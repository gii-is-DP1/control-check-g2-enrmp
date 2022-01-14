package org.springframework.samples.petclinic.feeding;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
	
@Controller
@RequestMapping("/feeding")
public class FeedingController {

	private static final String VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";

	private final FeedingService feedingService;
	
	@Autowired
	public FeedingController(FeedingService feedingService) {
		this.feedingService = feedingService;
	}
    
	@ModelAttribute("feedingTypes")
	public Collection<FeedingType> populatefeedingTypes() {
		return this.feedingService.getAllFeedingTypes();
	}
	
	@GetMapping(value = "/create")
	public String initCreationForm(Feeding feeding, ModelMap model) {
		Feeding f = new Feeding();
		model.put("feeding", f);
		return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Feeding f, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("feeding", f);
			return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
		}
		else {
                    try{
                    	this.feedingService.save(f);
                    }catch(UnfeasibleFeedingException ex){
                        result.rejectValue("feeding", "not for your pet type");
                        return VIEWS_FEEDINGS_CREATE_OR_UPDATE_FORM;
                    }
                    return "redirect:/welcome";
		}
	}
	
	
	
}
