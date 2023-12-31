package com.smd.umake.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smd.umake.dtos.BranchDTO;
import com.smd.umake.entities.Branch;
import com.smd.umake.exceptions.ArgumentInvalidException;
import com.smd.umake.exceptions.EntityNotFoundException;
import com.smd.umake.repositories.BranchRepository;

@Service
public class BranchService {

  @Autowired
  private BranchRepository branchRepository;

  public List<Branch> getBranchs() throws Exception {
    List<Branch> branchs = branchRepository.findAll();
    return branchs;
  }
  public Branch getBranchById(String branchId) throws Exception {
    try{
      UUID id = UUID.fromString(branchId);
      Optional<Branch> branch = branchRepository.findById(id);
      if(branch.isPresent()){
        return branch.get();
      }
      throw new EntityNotFoundException("Filial não encontrada!");
    } catch (IllegalArgumentException e){
      throw new ArgumentInvalidException("Id não é válido!");
    }
  }
  public Branch getBranchByName(String name) throws Exception {
    Optional<Branch> oBranch = branchRepository.findDistinctByName(name);
    if (oBranch.isPresent()){
      return oBranch.get();
    }
    throw new EntityNotFoundException("Filial não encontrada!");
  }
  public Branch createBranch(BranchDTO newBranch) throws Exception {
    int number = Integer.valueOf(newBranch.getNumber());
    Branch branch = new Branch();
    branch.setName(newBranch.getName());
    branch.setCEP(newBranch.getCEP());
    branch.setCity(newBranch.getCity());
    branch.setState(newBranch.getState());
    branch.setStreet(newBranch.getStreet());
    branch.setNumber(number);
    return branchRepository.save(branch);
  }

  public String deleteBranch(String branchID) throws Exception {
    try{
      UUID id = UUID.fromString(branchID);
      Optional<Branch> oBranch = branchRepository.findById(id);
        if (oBranch.isPresent()){
          branchRepository.delete(oBranch.get());
          return "Branch deleted!";
        } else {
          throw new EntityNotFoundException("Filial não encontrada!");
        }

    } catch (IllegalArgumentException e){
      throw new ArgumentInvalidException("Id não é válido!");
    }
  }

}
