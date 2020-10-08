package com.example.demo;

import com.example.demo.entity.Client;
import com.example.demo.entity.Software;
import com.example.demo.entity.repository.ClientRepository;
import com.example.demo.entity.repository.SoftwareRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class DemoApplication {
	private String adisc = "Alternatively referred to as antivirus software, AVS, antivir, or AV. An antivirus program is a software utility designed to protect your computer or network against computer viruses. If and when a virus is detected, the computer displays a warning asking what action should be done, often giving the options to quarantine, remove, ignore, or move the file to the vault.";
	private String wdisc = "Word processor is a software program capable of creating, storing, and printing typed documents. Today, the word processor is one of the most frequently used software programs on a computer, with Microsoft Word being the most popular word processor.";
	private String edisc = "Short for electronic mail, e-mail or email is information stored on a computer that is exchanged between two users over telecommunications. More plainly, e-mail is a message that may contain text, files, images, or other attachments sent through a network to a specified individual or group of individuals.";
	private String gdisc = "Game is software code designed to entertain or educate an individual. Today, computer gaming is a big business, and there are millions of different computer games that are enjoyed by people of all ages. The picture shows a screenshot of a computer game known as WarCraft II by Blizzard.";
	private String apdisc = "graphics program is a software application that allows you to draw, edit, and manipulate an image or graphic file. A good example of a graphics program is CorelDRAW.";
	private String vdisc = "To play a movie file on your computer, you must have a movie player program that supports the video file you're trying to play. For example, movie players that work with Windows are Windows Media Player (included with Windows) and VLC (that also works on other platforms).";

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200")
						.allowedMethods("GET","POST", "PUT","DELETE");
			}
		};
	}
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	@Bean
	public CommandLineRunner SoftwareListDB(SoftwareRepository softwareRepository, ClientRepository clientRepository) {
		return (args) -> {
			System.out.println("Timer task started at:" + new Date());
			Faker fakerClient = new Faker(new Locale("de"));
			Faker fakerSoftware = new Faker(new Locale("de"));
			int counter = 0;

//			for (int i = 0; i < 100; i++) {
//				Software software = new Software(fakerSoftware.dragonBall().character(), Long.toString(fakerSoftware.number().numberBetween(20L, 300L)), "Window, Mac, Linux", fakerSoftware.lorem().sentence());
//				softwareRepository.save(software);
//				counter++;
//				if (counter == 100) {
//					System.out.println("------> Tmp Software: " + Integer.toString(counter) + " added");
//					counter=0;
//				}
//			}
			softwareRepository.save(new Software("Antivirus", "115 Euro", "Window, Mac, Linux", adisc));
			softwareRepository.save(new Software("Word processor", "65 Euro", "Window, Mac, Linux", wdisc));
          softwareRepository.save(new Software("E-mail", "40 Euro", "Window, Mac, Linux", edisc));
          softwareRepository.save(new Software("Game", "20 Euro", "Window, Mac, Linux", gdisc));
          softwareRepository.save(new Software("Adobe Photoshop", "110 Euro", "Window, Mac, Linux", apdisc));
          softwareRepository.save(new Software("Video Player", "15 Euro", "Window, Mac, Linux", vdisc));
				log.info("All Softwares added:");

//			for (int i = 0; i < 100; i++) {
//				Client client = new Client(fakerClient.name().firstName(), fakerClient.name().lastName(), fakerClient.internet().emailAddress(), fakerClient.phoneNumber().phoneNumber() , fakerClient.number().numberBetween(18L, 100L));
//				clientRepository.save(client);
//				counter++;
//				if (counter == 100) {
//					System.out.println("------> Tmp Client: " + Integer.toString(counter) + " added");
//					counter=0;
//				}
//			}
			  clientRepository.save(new Client("Weber", "Schneider", "weber@fsi.de", "01242454558", 27L));
            clientRepository.save(new Client("Hoffmann", "Bauer", "hoffmann@bau.de", "0112444563", 33L));
            clientRepository.save(new Client("Wagner", "Braun", "braun@wag.de", "244422113", 53L));
            clientRepository.save(new Client("Lange", "Schmitt", "lange@schmitt.de", "0112324443", 42L));
			log.info("All Clients added:");
			System.out.println("Timer task finished at:" + new Date());
		};
	}

}
