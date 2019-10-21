package com.apologic.battledev.ctrl;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AbstractBattledevJUnit {

    private static Map<String, String> INPUT;

    private static Map<String, String> OUTPUT;

    private static Method ISOCONTEST_MAIN;

    private static ExecutorService ES;

    @BeforeClass
    public static void beforeClass() throws Exception {
        INPUT = new HashMap<>();
        OUTPUT = new HashMap<>();
        // on recupere la classe du projet + la methode main
        Class<?> isocontext = Thread.currentThread().getContextClassLoader().loadClass("com.isograd.exercise.IsoContest");
        ISOCONTEST_MAIN = Arrays.stream(isocontext.getDeclaredMethods()).filter(m -> m.getName().equals("main"))
                .filter(m -> m.isVarArgs() || m.getParameterCount() == 1 && m.getParameterTypes()[0].isArray())
                .peek(m -> m.setAccessible(true))
                .findFirst().orElseThrow(() -> new Exception("no main"));
        ES = Executors.newFixedThreadPool(2);
        // on recupere le zip du telechargement
        Path sample = Paths.get(".").toAbsolutePath().resolve("sample").normalize();
        try (Stream<Path> files = Files.walk(sample, 1)) {
            files.filter(sample.getFileSystem().getPathMatcher("glob:**/sample-*.zip")::matches).limit(1).forEach(path -> {
                System.out.println(path);
                Pattern input = Pattern.compile(".*/(input.+\\.txt)");
                Pattern output = Pattern.compile(".*/(output.+\\.txt)");
                try (ZipFile zipFile = new ZipFile(path.toFile())) {
                    zipFile.stream().filter(e -> !e.isDirectory())
                            .filter(e -> input.asPredicate().test(e.getName()))
                            .forEach(e -> save(input, zipFile, e, INPUT));
                    zipFile.stream().filter(e -> !e.isDirectory())
                            .filter(e -> output.asPredicate().test(e.getName()))
                            .forEach(e -> save(output, zipFile, e, OUTPUT));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (INPUT.isEmpty()) {
            throw new Exception("Aucun fichier zip  dans le dossier " + sample + " !");
        }
    }

    @AfterClass
    public static void afterClass() {
        ES.shutdown();
    }

    private static void save(Pattern pattern, ZipFile zipFile, ZipEntry e, Map<String, String> map) {
        String name = pattern.matcher(e.getName()).replaceFirst("$1");
        try (InputStream inputStream = zipFile.getInputStream(e)) {
            map.put(name, IOUtils.toString(inputStream));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void exemple(int exo) {
        exemple("input" + exo + ".txt", "output" + exo + ".txt");
    }

    protected void exemple(String input, String output) {
        if (!INPUT.containsKey(input)) {
            System.out.println("aucun exemple pour " + input);
            return;
        }
        Assert.assertTrue(output, OUTPUT.containsKey(output));

        OutputStream os = new ByteArrayOutputStream();
        PrintStream out = System.out;
        InputStream in = System.in;
        String exemple = INPUT.getOrDefault(input, "");
        try {
            // on change les flux de sortie / entree
            System.setOut(new PrintStream(os));
            System.setIn(new ByteArrayInputStream(exemple.getBytes()));
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
        String isofile = OUTPUT.getOrDefault(output, "").trim();

        System.out.println(input);
        System.out.println(exemple);
        System.out.println();
        System.out.println("IsoContest.java");
        System.out.println(isocontest);
        System.out.println();
        System.out.println(output);
        System.out.println(isofile);

        Assert.assertEquals(isofile, isocontest);
    }

}
