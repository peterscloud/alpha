package com.peterscloud.stream;

import org.junit.*;
import static org.junit.Assert.*;
import com.peterscloud.model.*;
import com.peterscloud.collection.*;

public class StreamTest {
  
  @Test
  public void reduce() {
    List<Integer> list = new ArrayList<>();
    int total = 0;
    for (int i = 0; i < 3; i++) {
      total += i;
      list.add(i);
    }

    Stream<Integer> stream = list.stream();
    assertNotNull("stream", stream);
    Integer totalReduce = stream.reduce(0, (a, b) -> a + b);
    assertEquals("total", Integer.valueOf(total), totalReduce);
  }
  
  @Test
  public void mapReduce() {
    List<Person> people = new ArrayList<>();
    int totalAge = 0;
    for (int i = 0; i < 3; i++) {
      totalAge += i;
      Person person = new Person();
      person.setAge(i);
      people.add(person);
    }

    Stream<Person> stream = people.stream();
    assertNotNull("stream", stream);
    Integer totalAgeReduce = stream.map(Person::getAge)
      .reduce(0, (a, b) -> a + b);
    assertEquals("totalAge", Integer.valueOf(totalAge), totalAgeReduce);


  
  }
  
  @Test
  public void filterCollect() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    List<Integer> even = list.stream().filter(v -> v % 2 == 0).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    assertNotNull("even", even);
    Iterator<Integer> iter = even.iterator();
    while (iter.hasNext()) {
      int v = iter.next();
      assertTrue(v + " is even", v % 2 == 0);
    }

  }

  @Test public void groupBy() {
    List<Person> people = new ArrayList<Person>();
    for (int i = 1; i < 10; i++) {
      for (int j = 0; j < i; j++) {
        Person person = new Person();
        person.setAge(i);
        people.add(person); 
      }
    }    
    
    Map<Integer, List<Person>> byAge =
    people
        .stream()
        .collect(
            Collectors.groupingBy(Person::getAge));
            
    Iterator<Map.Entry<Integer, List<Person>>> peopleByAge = byAge.entrySet().iterator();
    while (peopleByAge.hasNext()) {
      Map.Entry<Integer, List<Person>> entry = peopleByAge.next();
      List<Person> list = entry.getValue();
      int age = entry.getKey();
      assertEquals("size", age, list.size());
      Iterator<Person> persons = list.iterator();
      while (persons.hasNext()) {
        assertEquals("age", age, persons.next().getAge());
      }
    }    
  }

  @Test public void downstreamGroupingBy() {
    List<Employee> employees = new ArrayList<>();
    Map<Integer,Double> expected = new ArrayMap<>();
    double salary = 10.0;
    
    int departmentMaxId = 10;
    int countryMaxId = 20;
    int ageMax = 11;
    for (int departmentId = 1; departmentId <= departmentMaxId; departmentId++) {
      for (int countryId = 11; countryId <= countryMaxId; countryId++) {
        for (int age = 1; age < ageMax; age++) {
          Employee employee = new Employee(age, departmentId, salary * age, countryId);
          employees.add(employee);
        }
      }
    }

    System.out.println("employees.size: " + employees.size());
    
    Stream<Employee> stream = employees.stream();
    assertNotNull("stream", stream);
    
    Map<Integer, Map<Integer, List<Employee>>> employeesByCountryDepartment = 
      employees.stream()
      .collect(
        Collectors.groupingBy(Employee::getCountryId,
          Collectors.groupingBy(Employee::getDepartmentId))); 
          
    assertNotNull("employeesByCountryDepartment", employeesByCountryDepartment);
    Set<Map.Entry<Integer, Map<Integer, List<Employee>>>> set = employeesByCountryDepartment.entrySet();
    //System.out.println("set size: " + set.size());
    /*
    Iterator<Map.Entry<Integer, Map<Integer, List<Employee>>>> iter = employeesByCountryDepartment.entrySet().iterator();
    while (iter.hasNext()) {
       Map.Entry<Integer, Map<Integer, List<Employee>>> entry = iter.next();
       System.out.println("set key: " + entry.getKey());
       
    }
*/
    
    for (int countryId = 11; countryId <= countryMaxId; countryId++) {
      Map<Integer, List<Employee>> employeesByDepartment = employeesByCountryDepartment.get(countryId);
      assertNotNull("employeesByDepartment " + countryId, employeesByDepartment);
      for (int departmentId = 1; departmentId <= departmentMaxId; departmentId++) {
        List<Employee> emps = employeesByDepartment.get(departmentId);
        assertNotNull("emps " + departmentId, emps);
        Iterator<Employee> emp = emps.iterator();
        
        while (emp.hasNext()) {
          Employee employee = emp.next();
          assertEquals("deplartmentId", departmentId, employee.getDepartmentId());
          assertEquals("countryId", countryId, employee.getCountryId());
        }
      }
    }
  }


  @Test
  public void testStream2() {
    Stream2<String> stream2 = null;
    if (stream2 != null) {
      Stream<String> s2p = stream2.parallel();
    }

  }

}
