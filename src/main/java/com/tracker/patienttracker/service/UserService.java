package com.tracker.patienttracker.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void userApproval(int userId)
	{
		User u=userRepository.findById(userId).get();
		u.setApproved(1);
		userRepository.save(u);
	}
	@Transactional
	public void userDenial(int userId)
	{
		User u=userRepository.findById(userId).get();
		u.setApproved(-1);
		userRepository.save(u);
	}
	public Set<User> userApprovalPendingList()
	{
		return userRepository.userApprovalPendingList();
	}
}
