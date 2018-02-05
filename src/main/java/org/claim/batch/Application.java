package org.claim.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	
	static ApplicationContext context =
			new ClassPathXmlApplicationContext("launch-context.xml");

	public static void main(String[] args) {
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("job1");
		JobParameters params = new JobParameters();
		JobParameter paramter = new JobParameter(new Date());
		params.getParameters().clear();
		params.getParameters().put("jobmaster1", paramter);
		JobExecution execution;
		try {
			execution = jobLauncher.run(job, params);
			System.out.println("Status : " + execution.getStatus() + ""
					+ "Exit Status : " + execution.getJobInstance().getId());
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	/*	try {
			JobParameter paramterTwo = new JobParameter(new Date());
			params.getParameters().clear();
			params.getParameters().put("jobmaster2", paramterTwo);
			jobLauncher.run(job, params);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
