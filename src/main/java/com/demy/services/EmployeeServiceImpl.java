package com.demy.services;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demy.Entites.EmployeeEntity;
import com.demy.repositories.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	
	
	
	
	
	@Override
	public EmployeeEntity employeeLogin(String email, String password) 
	{
		
		return employeeRepository.findByEmailAndPassword(email, password);
	}
	
	
	@Override
	public String generateOtp() 
	{
	    String allowedChars = "0123456789";
	    SecureRandom random = new SecureRandom();
	    StringBuilder otp = new StringBuilder(4);

	    // Generate OTP of specified length
	    for (int i = 0; i < 4; i++) 
	    {
	        int index = random.nextInt(allowedChars.length());
	        otp.append(allowedChars.charAt(index));
	    }
	    return otp.toString();
	}
	
	@Override
	public HttpStatus sendEmail(String email,String otp) 
	{
		try {
		    SimpleMailMessage message = new SimpleMailMessage();
		    message.setSubject("Your Otp Code For Forgot Password");
		    message.setText("Your OTP is: " + otp);
		    message.setTo(email);

		    javaMailSender.send(message);

		    return HttpStatus.OK; // Email sent successfully
		} catch (Exception e) {
		    e.printStackTrace();
		    return HttpStatus.INTERNAL_SERVER_ERROR; // Internal Server Error		}
		// TODO Auto-generated method stub
		}
	}

	@Override
	public EmployeeEntity findEmail(String email)
	{
		
		return employeeRepository.findByEmail(email);
	}

	@Override
	public void updatePassword(String email, String password) {
		// TODO Auto-generated method stub
		System.out.println(email+password);
	employeeRepository.updatePasswordByEmail(email,password);
	}

	@Override
	public List<EmployeeEntity> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeEntity addEmployee(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public HttpStatus sendAddEmail(String email,String user) {
		try {
		    SimpleMailMessage message = new SimpleMailMessage();
		    message.setSubject("Congratulations on Your Onboarding to DSS Software Company!");
		    message.setText("Dear "+user+",\r\n"
		    		+ "\r\n"
		    		+ "On behalf of the entire HR team at DSS Software Company, I would like to extend our warmest congratulations to you on your successful onboarding to our esteemed organization!\r\n"
		    		+ "\r\n"
		    		+ "We are thrilled to have you join our team and embark on this exciting journey together. Your skills, experience, and dedication will undoubtedly contribute significantly to our company's success.\r\n"
		    		+ "\r\n"
		    		+ "We understand that starting a new role can be both exhilarating and challenging, but please rest assured that our team is here to support you every step of the way. Should you have any questions or need assistance with anything, please do not hesitate to reach out to us.\r\n"
		    		+ "\r\n"
		    		+ "Once again, congratulations on becoming a part of the DSS Software Company family! We look forward to seeing your remarkable contributions and wish you all the best as you begin this new chapter in your career.\r\n"
		    		+ "\r\n"
		    		+ "Warm regards,\r\n"
		    		+ "\r\n"
		    		+ "Mahalakshmi\r\n"
		    		+ "HR Team\r\n"
		    		+ "DSS Software Company");
		    message.setTo(email);

		    javaMailSender.send(message);

		    return HttpStatus.OK; // Email sent successfully
		} catch (Exception e) 
		{
		    e.printStackTrace();
		    return HttpStatus.INTERNAL_SERVER_ERROR; // Internal Server Error		}
		// TODO Auto-generated method stub
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

	@Override
	public EmployeeEntity editEmployee(EmployeeEntity employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EmployeeEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id);
	}

	@Override
	public void update(EmployeeEntity updateEmp) {
		
		employeeRepository.save(updateEmp);
		
	}

	@Override
	public Long employeeCount() {
		// TODO Auto-generated method stub
		return employeeRepository.count();
	}

	
}
	// TODO Auto-generated method stub
	

