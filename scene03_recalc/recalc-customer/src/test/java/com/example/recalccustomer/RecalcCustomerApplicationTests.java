package com.example.recalccustomer;

import com.example.recalccustomer.pojo.Customer;
import com.example.recalccustomer.pojo.CustomerConfig;
import com.example.recalccustomer.service.CustomerConfigService;
import com.example.recalccustomer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class RecalcCustomerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CustomerService customerService;

    @Test
    void saveCustomer() {
        List<Customer> customerList = new ArrayList<>();
        long startTime = System.currentTimeMillis(); //获取开始时间
        for (int i = 10000; i <= 100000; i++) {
            Customer customer = new Customer();
            customer.setCustomerNam("客户"+i);
            customerList.add(customer);
        }
        customerService.saveBatch(customerList);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
    }

    @Autowired
    private CustomerConfigService customerConfigService;

    @Test
    void saveCustomerConfig() {
        List<CustomerConfig> customerList = new ArrayList<>();
        long startTime = System.currentTimeMillis(); //获取开始时间
        for (int i = 10000; i <= 100000; i++) {
            CustomerConfig customerConfig = new CustomerConfig();
            long j = new Long(i);
            customerConfig.setCustomerId(j);
            customerConfig.setWeightRate(randomDouble());
            customerList.add(customerConfig);
        }
        customerConfigService.saveBatch(customerList);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //程序运行时间：8360ms
    }

    public static Double randomDouble(){
        Random random = new Random();
        double num = random.nextDouble() * 2;
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(num);
        return  Double.parseDouble(format);
    }
}
