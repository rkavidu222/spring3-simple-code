package com.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepo customerRepo;

    public Main(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @GetMapping
    public List<Customer> getCustomers(){

        return customerRepo.findAll();
    }

    record NewCustomerRequest(Integer age,  String email, String name ) {

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setAge(request.age);
        customer.setEmail(request.email);
        customer.setName(request.name);
        customerRepo.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){

        customerRepo.deleteById(id);
    }

    

    /*
    @GetMapping("/greet")
    public  GreetResponse greet(){
        GreetResponse response = new GreetResponse(
                "Hello",
                List.of("java", "C++", "C#"),
                new Person("Kavindu", 24, 15000.00)
        );
        return response;
    }

    record Person(String name, int age, double cash ){

    }
    record GreetResponse (
            String greet,
            List<String> programmingLanguages,
            Person person
    ){}


    public class GreetResponse{
        private final String greet;

        public GreetResponse(String greet){
            this.greet= greet;
        }

        public String getGreet() {
            return greet;
        }

        @Override
        public String toString() {
            return "GreetResponse{" +
                    "greet='" + greet + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GreetResponse that = (GreetResponse) o;
            return Objects.equals(greet, that.greet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(greet);
        }
    }
*/
}
