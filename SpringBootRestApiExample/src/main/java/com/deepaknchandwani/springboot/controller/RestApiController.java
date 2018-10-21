package com.deepaknchandwani.springboot.controller;

 import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.deepaknchandwani.springboot.model.User;
import com.deepaknchandwani.springboot.service.UserService;
import com.deepaknchandwani.springboot.util.CustomErrorType;
import com.twilio.http.HttpMethod;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Hangup;
import com.twilio.twiml.voice.Pause;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Say.Language;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET  , produces= MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<User>> listAllUsers(@RequestParam("id") String personId) {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		System.out.println(new Date());
		System.out.println(personId);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping("/tracker")
	public ResponseEntity<Object> redirectToExternalUrl(@RequestParam("url") String url) throws URISyntaxException {
	    URI yahoo = new URI("http://www."+url);
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(yahoo);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	@RequestMapping(value = "/usa/", method = RequestMethod.POST , produces={ MediaType.APPLICATION_XML_VALUE})
	public String getTwillioResponse(@RequestParam("name") String name) { 
		
	/*	  Say say1 = new Say.Builder(" Jay Shree Ram! Happy Dusshera! Jay Shree Ram! Happy Dusshera!, Dear "+name + 
				  " Wish you a very Happy Dusshera and Navratras from  Mukesh Matai Family." ).voice(Say.Voice.WOMAN)
		            .language(Language.EN_IN).build();
  Say say2 = new Say.Builder("May Lord Rama keep lighting your path of success and may you achieve victory in every phase of life. Dear! "+name + 
  		 
  		" Convey over Regards to Elders and Lots of Love to little ones, With Best Wishes From Mukesh Matai Family.  ").voice(Say.Voice.WOMAN)
	            .language(Language.EN_IN).build();
 	        VoiceResponse response = new VoiceResponse.Builder().
	        		say(say1).say(say2).build();*/
		
		  Say say1 = new Say.Builder("Jay Sant Saahib Hirdaaraam Ji, Jay Sant Saahib Hirdaaraam Ji!  Dear "+name + 
				  " This is a Reminder for Papas water drinking time" ).voice(Say.Voice.WOMAN)
		            .language(Language.EN_IN).build();
  Say say2 = new Say.Builder("Jay Sant Saahib Hirdaaraam Ji, Jay Sant Saahib Hirdaaraam Ji!  Dear "+name + 
		  " This is a Reminder for Papas water drinking time" ).voice(Say.Voice.WOMAN)
          .language(Language.EN_IN).build();
 	        VoiceResponse response = new VoiceResponse.Builder().
	        		say(say1).say(say2).build();	

		
	/*    Say say = new Say
	            .Builder("Please enter your account number,\nfollowed by the pound sign").build();
	        Gather gather = new Gather.Builder().action("https://e3949f1b.ngrok.io/vishnumishra/api/processresponse/")
	            .method(HttpMethod.POST).say(say).build();
	        Say say2 = new Say
	            .Builder("We didn't receive any input. Goodbye!").build();
	        VoiceResponse response = new VoiceResponse.Builder().gather(gather)
	            .say(say2).build();*/
	        
     return response.toXml();
		
		
	}
	
	
	
	@RequestMapping(value = "/processresponse/", method = RequestMethod.POST , produces={ MediaType.APPLICATION_XML_VALUE})
	public String processResponse(@RequestParam("Digits") String Digits) { 
		System.out.println(Digits);
		Say say = new Say.Builder("Deepak chandwani you entered following digits , "+Digits+", Thanks").voice(Say.Voice.WOMAN)
	            .language(Language.EN_IN).build();
	        VoiceResponse response = new VoiceResponse.Builder().say(say).build();

     return response.toXml();
		
		
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}