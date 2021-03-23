package view;

import java.util.concurrent.Semaphore;

import controller.SaqueDeposito;


public class Principal{

	public static void main(String[] args)
	{
		int permissoes = 1;
		Semaphore semaforo1 = new Semaphore(permissoes);
		Semaphore semaforo2 = new Semaphore(permissoes);
		
		for (int idTransacao = 1; idTransacao < 21; idTransacao++)
		{
			Thread thread = new SaqueDeposito(idTransacao, semaforo1, semaforo2);
			thread.start();
		}

	}

}