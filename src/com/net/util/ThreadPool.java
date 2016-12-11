package com.net.util;

import java.util.LinkedList;
/**
 * Working thread pool
 * 
 * huilet 2013-4-17
 * @author yuanc
 */
public class ThreadPool extends ThreadGroup {
	/** Thread pool is closed*/
	private boolean isClosed = false; 
	/** Job queue */
	private LinkedList workQueue; 
	/** Thread poolid */
	private static int threadPoolID = 1; 

	private static ThreadPool instance;

	public static ThreadPool getInstance(int poolSize) {
		if (instance == null) {
			instance = new ThreadPool(poolSize);
		}
		return instance;
	}
	/**
	 * 
	 * @param poolSize
	 * 			Represents the number of threads in the thread pool
	 * huilet 2013-4-17
	 * @author yuanc
	 */
	private ThreadPool(int poolSize) {
		// 指定ThreadGroup的名称
		super(threadPoolID + ""); 
		// 继承到的方法，设置是否守护线程池
		setDaemon(true); 
		// 创建工作队列
		workQueue = new LinkedList(); 
		for (int i = 0; i < poolSize; i++) {
			// 创建并启动工作线程,线程池数量是多少就创建多少个工作线程
			new WorkThread(i).start(); 
		}
	}

	/**
	 * Add a new task to the job queue,The task is performed by the worker thread
	 * @param task
	 * huilet 2013-4-17
	 * @author yuanc
	 */
	public synchronized void execute(Runnable task) {
		if (isClosed) {
			throw new IllegalStateException();
		}
		/*
		 * Here you can consider setting the thread pool to wait for the thread size、Time mechanism if(workQueue.size()>30) {
		 * //Thread.sleep(3000); }
		 */
//		if(workQueue.size()>30) {    //线程数量超过30则休眠5秒
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		if (task != null) {
			// 向队列中加入一个任务
			workQueue.add(task);
			// 唤醒一个正在getTask()方法中待任务的工作线程
			notify();
		}
	}

	/**
	 * Take a task from the job queue,The working thread will call this method
	 * @param threadid
	 * @return
	 * @throws InterruptedException
	 * huilet 2013-4-17
	 * @author yuanc
	 */
	private synchronized Runnable getTask(int threadid)
			throws InterruptedException {
		while (workQueue.size() == 0) {
			if (isClosed)
				return null;
			System.out.println("OPT Thread" + threadid + "Waiting Task...");
			// 如果工作队列中没有任务,就等待任务
			wait(); 
		}
		System.out.println("OPT Thread " + threadid + " Excute Task...");
		// 返回队列中第一个元素,并从队列中删除
		return (Runnable) workQueue.removeFirst(); 
	}

	/**
	 * Close thread pool
	 * 
	 * huilet 2013-4-17
	 * @author yuanc
	 */
	public synchronized void closePool() {
		if (!isClosed) {
			// 等待工作线程执行完毕
			waitFinish(); 
			isClosed = true;
			// 清空工作队列
			workQueue.clear(); 
			// 中断线程池中的所有的工作线程,此方法继承自ThreadGroup类
			interrupt(); 
		}
	}

	/**
	 * Wait for the worker thread to execute all the tasks 
	 * 
	 * huilet 2013-4-17
	 * @author yuanc
	 */
	public void waitFinish() {
		synchronized (this) {
			isClosed = true;
			// 唤醒所有还在getTask()方法中等待任务的工作线程
			notifyAll();
		}
		// activeCount()
		// 返回该线程组中活动线程的估计值。
		Thread[] threads = new Thread[activeCount()];
		// enumerate()方法继承自ThreadGroup类，根据活动线程的估计值获得线程组中当前所有活动的工作线程
		int count = enumerate(threads);
		// 等待所有工作线程结束
		for (int i = 0; i < count; i++) {
			try {
				// 等待工作线程结束
				threads[i].join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Internal class,Working thread,Be responsible for taking out tasks from the job queue.,And execute
	 * 
	 * huilet 2013-4-17
	 * 
	 * @author yuanc
	 */
	private class WorkThread extends Thread {
		private int id;

		public WorkThread(int id) {
			// 父类构造方法,将线程加入到当前ThreadPool线程组中
			super(ThreadPool.this, id + "");
			this.id = id;
		}

		public void run() {
			// isInterrupted()方法继承自Thread类，判断线程是否被中断
			while (!isInterrupted()) {
				Runnable task = null;
				try {
					// 取出任务
					task = getTask(id);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				// 如果getTask()返回null或者线程执行getTask()时被中断，则结束此线程
				if (task == null)
					return;
				try {
					// 运行任务
					task.run();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}
}