package com.ntk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ntk.constant.DateTimeConstant;
import com.ntk.constant.UserRole;
import com.ntk.model.CreditCardTypeModel;
import com.ntk.model.TransactionModel;
import com.ntk.model.UserModel;
import com.ntk.model.UserRoleModel;
import com.ntk.repository.CreditCardTypeRepository;
import com.ntk.repository.TransactionRepository;
import com.ntk.repository.UserRepository;
import com.ntk.repository.UserRoleRepository;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
public class CreditCardManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(TransactionRepository tRepo, CreditCardTypeRepository cctRepo, UserRepository repo, UserRoleRepository userRole) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// UserModel model = UserModel.builder().name("Khiet")
				// 		.email("admin@school.com")
				// 		.password("admin")
				// 		.phoneNumber("0934965324")
				// 		.role(UserRole.TEACHER.toString()).build();
				// repo.save(model);
				// userRole.save(new UserRoleModel(null, "TEACHER"));

				// CreditCardTypeModel cctModel = CreditCardTypeModel.builder()
				// .bankId("STB")
				// .name("Sacombank Visa")
				// .interstFreePeriod(Integer.valueOf(55))
				// .build();
				// cctRepo.save(cctModel);
				// int interestFreePeriod =  cctModel.getInterstFreePeriod();
				
				// LocalDateTime transactionDate = DateTimeConstant.convertLocalDateTime("06/10/2024", DateTimeConstant.ddMMyyyyFormatter);
				// LocalDateTime cutOffDate = transactionDate.plusDays(interestFreePeriod);
				// String creditCardType = cctModel.getBankId() + " " + cctModel.getName();

				// TransactionModel tModel = TransactionModel.builder()
				// 							.creditCardNumber("123456")
				// 							.userName("khiet")
				// 							.chatId(Long.valueOf(995461642))
				// 							.transactionDate(transactionDate)
				// 							.cutOffDate(transactionDate)
				// 							.creditCardType(creditCardType)
				// 							.build();
				// tRepo.save(tModel);

		
				
			}
		};
	}
}
