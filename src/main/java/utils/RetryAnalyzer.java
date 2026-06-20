package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxTry = 4;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxTry) {
            retryCount++;
            return true;
        }
        return false;
    }
}
