package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
	
	private int idCarro;
	private Semaphore semaforo, acessoSentido;
	private static String sentido;
	
	public ThreadCarro(int idCarro, Semaphore semaforo, Semaphore acessoSentido) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
		this.acessoSentido = acessoSentido;
	}
	
	@Override
	public void run() {
		paradoSemaforo();
		try {
			//---------P (Acquire)---------
			semaforo.acquire();
			sinalVerde();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//---------V (Release)---------
			semaforo.release();
			carroAndando();					
		}
	}
	
	private void paradoSemaforo() {
		System.out.println("#" + idCarro + "está esperando sinal verde para passar.");
		int tempo = 400;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	
	private void sinalVerde() {
		System.out.println("#" + idCarro + "recebeu sinal verde para passar.");
	switch (idCarro) {
			
			case 0:
				sentido = "Norte";
				break;
			
			case 1:
				sentido = "Sul";
				break;
				
			case 2:
				sentido = "Leste";
				break;
				
			case 3:
				sentido = "Oeste";
				break;
			}
			System.out.println("#" + idCarro + " andou 100m no sentido " +sentido+".");
	}
	
	
	private void carroAndando() {
		int distanciaTotal = (int)((Math.random()*501) + 1500);
		int distanciaPercorrida =100;
		int deslocamento = 100;
		int tempo = 30;
		while(distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			acessoSentido.acquire();
			switch (idCarro) {
			
			case 0:
				sentido = "Norte";
				break;
			
			case 1:
				sentido = "Sul";
				break;
				
			case 2:
				sentido = "Leste";
				break;
				
			case 3:
				sentido = "Oeste";
				break;
			}
			System.out.println("#" + idCarro + " andou " + distanciaPercorrida + "m no sentido " +sentido+".");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			acessoSentido.release();
		}
		}		
	}

}
