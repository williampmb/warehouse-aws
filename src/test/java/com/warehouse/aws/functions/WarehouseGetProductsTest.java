package com.warehouse.aws.functions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.warehouse.aws.models.HttpRequestWarehouse;
import com.warehouse.aws.models.HttpResponseWarehouse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class WarehouseGetProductsTest {

    private static HttpRequestWarehouse input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = new HttpRequestWarehouse();
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testWarehouseGetProducts() {
        WarehouseGetProducts handler = new WarehouseGetProducts();
        Context ctx = createContext();

        HttpResponseWarehouse response = handler.handleRequest(input, ctx);

        Assert.assertEquals("Hello from Lambda!", response.toString());
    }
}
