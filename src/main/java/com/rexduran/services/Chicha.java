package com.rexduran.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Chicha {
	Integer i;

	private synchronized Integer getId() {
		return this.i++;

	}

	private void setZeroId() {
		this.i = 0;
	}

	int k = 0;

	public void futureRunning(Integer cant) {

		setZeroId();
		Executor executor = Executors.newFixedThreadPool(cant);

		for (k = 0; k < cant; k++) {
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
				Integer valor = 0;
				try {

					// System.out.println("Inicio hilo en ejecución " +
					// Thread.currentThread().getName() +" k="+k);
					valor = getId();
					// System.out.println("Hilo en ejecución " + Thread.currentThread().getName() +
					// " ID=" + valor +" k="+k);
					TimeUnit.SECONDS.sleep(0);
				} catch (InterruptedException e) {
					throw new IllegalStateException(e);
				}
				System.out.println("Fin en ejecución " + Thread.currentThread().getName() + " ID=" + valor + " k=" + k);
				return "Result of the asynchronous computation";
			}, executor);
		}

	}
}
