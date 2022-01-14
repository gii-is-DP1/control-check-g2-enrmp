package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="feeding")
public class Feeding extends BaseEntity{
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name="start_date")
	@NotEmpty
    LocalDate startDate;
	
	@Column(name="weeks_duration")
	@Min(1)
	@NotEmpty
    double weeksDuration;
	
	@ManyToOne
	@NotEmpty
    @JoinColumn(name="pet_id")
    Pet pet;   
	
	@NotEmpty
	@ManyToOne
    @JoinColumn(name="feeding_type_id")
    FeedingType feedingType;
}
