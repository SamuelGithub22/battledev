package com.apologic.battledev.ctrl;

import org.apache.commons.lang3.function.Failable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AbstractBattledevJUnit {

    private static Method ISOCONTEST_MAIN;

    private static Path sample;

    private static ExecutorService ES;

    @BeforeAll
    public static void beforeClass() throws Exception {
        // on recupere la classe du projet + la methode main
        Class<?> isocontext = Thread.currentThread().getContextClassLoader().loadClass("com.isograd.exercise.IsoContest");
        ISOCONTEST_MAIN = Arrays.stream(isocontext.getDeclaredMethods()).filter(m -> m.getName().equals("main"))
                .filter(m -> m.isVarArgs() || m.getParameterCount() == 1 && m.getParameterTypes()[0].isArray())
                .peek(m -> m.setAccessible(true))
                .findFirst().orElseThrow(() -> new Exception("no main"));
        ES = Executors.newFixedThreadPool(2);
        // on recupere le zip du telechargement
        sample = Paths.get(".").toAbsolutePath().resolve("sample").normalize();
        try (Stream<Path> files = Files.walk(sample, 1)) {
            files.filter(sample.getFileSystem().getPathMatcher("glob:**/sample-*.zip")::matches)
                    .limit(1)
                    .forEach(Failable.asConsumer(path -> {
                        System.out.println(path);
                        try (ZipFile zipFile = new ZipFile(path.toFile())) {
                            zipFile.stream()
                                    .filter(e -> !e.isDirectory())
                                    .forEach(Failable.asConsumer(e -> save(zipFile, e)));
                        }
                        // on dezip et on delete la 1ere fois
                        Files.delete(path);
                    }));
        }
    }

    static Stream<Arguments> exemple() throws IOException {
        Set<String> txts;
        try (Stream<Path> files = Files.list(sample)) {
            txts = files.map(Path::getFileName).map(Path::toString).filter(str -> str.endsWith(".txt")).collect(Collectors.toSet());
        }
        return txts.stream().filter(str -> str.startsWith("input")).map(str -> str.substring(5, str.indexOf("."))).map(Integer::parseInt)
                .filter(i -> txts.contains("output" + i + ".txt"))
                .map(Failable.asFunction(i -> Arguments.of(i,
                        "input" + i + ".txt",
                        Files.readString(sample.resolve("input" + i + ".txt")),
                        "output" + i + ".txt",
                        Files.readString(sample.resolve("output" + i + ".txt")))));
    }

    @AfterAll
    public static void afterClass() {
        ES.shutdown();
    }

    private static void save(ZipFile zipFile, ZipEntry e) throws IOException {
        try (InputStream is = zipFile.getInputStream(e)) {
            Files.copy(is, sample.resolve(Path.of(e.getName()).getFileName()));
        }
    }

    private static void save(Pattern pattern, ZipFile zipFile, ZipEntry e, Map<String, String> map) {
        String name = pattern.matcher(e.getName()).replaceFirst("$1");
        try (InputStream inputStream = zipFile.getInputStream(e)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            map.put(name, bufferedReader.lines().collect(Collectors.joining("\n")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void hasAnyTest() {
        assertThat(sample).isDirectoryContaining("regex:.*input\\d+\\.txt");
        assertThat(sample).isDirectoryContaining("regex:.*output\\d+\\.txt");
    }

    @ParameterizedTest(name = "exemple{0}")
    @MethodSource
    @Order(2)
    public void exemple(int exemple, String inputFile, String input, String outputFile, String output) {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream out = System.out;
        InputStream in = System.in;
        try {
            // on change les flux de sortie / entree
            System.setOut(new PrintStream(os));
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Future<?> futur = ES.submit(() -> {
                try {
                    ISOCONTEST_MAIN.invoke(null, new Object[]{new String[0]});
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                } catch (InvocationTargetException e) {
                    throw new AssertionError(e.getCause());
                }
            });
            // on attend max 10s
            try {
                futur.get(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            } catch (ExecutionException e) {
                if (e.getCause() instanceof AssertionError) {
                    throw (AssertionError) e.getCause();
                } else {
                    throw new AssertionError(e.getCause());
                }
            } catch (TimeoutException e) {
                futur.cancel(true);
                throw new AssertionError(e);
            }

        } finally {
            System.out.flush();
            System.setOut(out);
            System.setIn(in);
        }
        String isocontest = os.toString().trim();

        System.out.println(inputFile);
        System.out.println(input);
        System.out.println();
        System.out.println("IsoContest.java");
        System.out.println(isocontest);
        System.out.println();
        System.out.println(outputFile);
        System.out.println(output);

        assertThat(output).isEqualTo(isocontest);
    }

}
