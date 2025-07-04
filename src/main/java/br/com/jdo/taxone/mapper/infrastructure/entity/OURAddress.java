package br.com.jdo.taxone.mapper.infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OURAddress{

  @Id
  private Integer id;
  private String name;

  @OneToOne(mappedBy="OURAddress")
  private Customer customer;

  public Integer getId(){
    return id;
  }
  
  public void setId(Integer id){
    this.id = id;
  }

  public String getName(){
    return name;
  }
  
  public void setName(String name){
    this.name = name;
  }

  public Customer getCustomer(){
    return customer;
  }
  
  public void setCustomer(Customer customer){
    this.customer = customer;
  }

}