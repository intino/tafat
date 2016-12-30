package io.intino.tafat;

import io.intino.tafat.engine.Executor;
import io.intino.tafat.engine.utils.Random;
import io.intino.tara.magritte.Graph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static spark.Spark.*;

public class TafatPlatform extends GraphWrapper implements io.intino.tara.magritte.Platform {

    private static final Logger LOG = Logger.getLogger(TafatPlatform.class.getName());
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private Executor executor;
    private Future<?> submission;
    private int delay = 1000;

    public TafatPlatform(Graph graph) {
        super(graph);
        Random.init(simulation().seed());
    }

    private void runProfiling() {
        if (profiling() == null) return;
        java.util.Random random = profiling().seed() == -1 ? new java.util.Random() : new java.util.Random(profiling().seed());
        profiling().profilerList().forEach(p -> p.execute(graph, random));
    }

    public void init() {
        runProfiling();
        executor = new Executor(graph);
        executor.init();
        initUserInterface();
    }

    @Override
    public void execute(String... args) {
        if (args.length > 0 && userInterface() != null)
            userInterface().port(Integer.parseInt(args[0]));
        init();
        if (userInterface() != null) return;
        executor.execute();
    }

	//TODO
	public void run() {
		if (userInterface() != null) return;
		executor.run(1);
	}

    public void run(int stepSize) {
        if (userInterface() != null) return;
        executor.run(stepSize);
    }

    public void stop() {
        System.exit(0);
    }

    private void initUserInterface() {
        if (userInterface() != null) initServer();
    }

    private void initServer() {
        port(userInterface().port());
        staticFileLocation("/web");
        get("/interfaceConfiguration", (req, res) -> userInterface().data());
        get("/values", (req, res) -> userInterface().values());
        get("/state", (req, res) -> "OK");
        get("/play", (req, res) -> {
            LOG.info("Simulation play: " + req.session().id());
            submission = executorService.submit((Runnable) () -> {
                while (true) {
                    try {
                        executor.run(1);
                        sleep(delay);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            return "OK";
        });
        get("/pause", (req, res) -> {
            LOG.info("Simulation pause: " + req.session().id());
            cancelSubmission();
            return "OK";
        });
        get("/reset", (req, res) -> {
            LOG.info("Simulation reloaded: " + req.session().id());
            cancelSubmission();
            reloadModel();
            return "OK";
        });
        post("/speed/:speed", (req, res) -> {
            LOG.info("Simulation speed changed: " + req.session().id());
            double speed = Double.parseDouble(req.params(":speed"));
            delay = (int) (1000 / speed);
            return "OK";
        });
    }

    private void reloadModel() {
        graph.reload();
        executor = new Executor(graph);
        executor.init();
    }

    private void cancelSubmission() {
        if (submission != null) {
            submission.cancel(true);
            submission = null;
        }
    }

}