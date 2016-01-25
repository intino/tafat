package tafat;

import tara.magritte.Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static spark.Spark.*;
import static tafat.engine.Date.getDateTime;

public class TafatEngine extends tafat.ModelWrapper implements tara.magritte.Engine {

	private static final Logger LOG = Logger.getLogger(TafatEngine.class.getName());
	private static ExecutorService executorService = Executors.newFixedThreadPool(1);
	private Future<?> submission;
	private int speed = 1000;
	Executor executor;

	public TafatEngine(Model model) {
		super(model);
	}

	@Override
	public void init() {
		executor = new Executor(_model);
		executor.init();
		initUserInterface();
	}

	@Override
	public void execute() {
		if (simulation().userInterface() != null) return;
		executor.execute();
	}

	public void run() {
		if (simulation().userInterface() != null) return;
		executor.run();
	}

	private void initUserInterface() {
		if (simulation().userInterface() != null) initServer();
	}

	private void initServer() {
		port(simulation().userInterface().port());
		staticFileLocation("/public");
		get("/interfaceConfiguration", (req, res) -> simulation().userInterface().data());
		get("/values", (req, res) -> simulation().userInterface().values());
		get("/time", (req, res) -> getDateTime());
		get("/state", (req, res) -> "OK");
		get("/play", (req, res) -> {
			LOG.info("Simulation play: " + req.session().id());
			submission = executorService.submit((Runnable) () -> {
				while (true) {
					executor.run();
					try {
						sleep(speed);
					} catch (InterruptedException ignored) {
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
			_model.reload();
			cancelSubmission();
			return "OK";
		});
	}

	private void cancelSubmission() {
		if(submission != null){
			submission.cancel(false);
			submission = null;
		}
	}

}