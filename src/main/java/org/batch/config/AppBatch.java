package org.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

@Profile("spring")
public class AppBatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppBatch.class);

    public static void main(final String[] args) {
        // Spring Java config
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().addActiveProfile("spring");
        context.register(SpringBatchConfig.class);

        context.refresh();

        // Spring xml config
        // ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-intro.xml");

        runJob(context, "firstBatchJob");
        //runJob(context, "skippingBatchJob");//Similar but it has validation. It uses invalid input file. If not valid, it stops and throws exception
        //runJob(context, "skipPolicyBatchJob");//Similar, but it has policy validation. It uses invalid input file. If not valid, it stops and throws exception
    }

    private static void runJob(AnnotationConfigApplicationContext context, String batchJobName) {
        final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        final Job job = (Job) context.getBean(batchJobName);

        LOGGER.info("Starting the batch job: {}", batchJobName);
        try {
            // To enable multiple execution of a job with the same parameters
            JobParameters jobParameters = new JobParametersBuilder().addString("jobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
            final JobExecution execution = jobLauncher.run(job, jobParameters);
            LOGGER.info("Job Status : {}", execution.getStatus());
        } catch (final Exception e) {
            e.printStackTrace();
            LOGGER.error("Job failed {}", e.getMessage());
        }
    }
}