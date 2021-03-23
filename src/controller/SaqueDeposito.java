package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class SaqueDeposito extends Thread
{
	private Semaphore semaforo1;
	private Semaphore semaforo2;
	private int idTransacao, tipo;
	private double valor;
	
	DecimalFormat formato = new DecimalFormat("#.##");

	public SaqueDeposito(int idTransacao, Semaphore semaforo1, Semaphore semaforo2)
	{
		this.idTransacao = idTransacao;
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
	}

	@Override
	public void run()
	{

		tipoTransacao();
		Transacao();

	}

	private void tipoTransacao()
	{
		tipo = (int) ((Math.random() * 101) + 1);
		valor = ((Math.random() * 201) + 10);
	}

	private boolean Par(int tipo)
	{
		if(tipo % 2 == 0)
		{
			return true;
		}
		return false;
	}

	
	private void Transacao()
	{  
		
			if(Par(tipo)){
				try
				{
					semaforo1.acquire();
					System.err.println("#" + idTransacao + ": Efetuando saque: ");
					sleep(2000);
					System.err.println("Transação #" + idTransacao + " - Saque no valor de: " + (formato.format(valor)));
					
				
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				semaforo1.release();
			}
		}
		else
		{
			try
			{
				semaforo2.acquire();
				System.out.println("#" + idTransacao + ": Efetuando deposito: ");
				sleep(2000);
				System.out.println("Transação #" + idTransacao + " - Deposito no valor de: " + (formato.format(valor)));
				
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				semaforo2.release();
			}
		}
		}
		
	}
