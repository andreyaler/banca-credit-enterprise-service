package com.bootcamp.banca.model;

import com.bootcamp.banca.entity.Credit;
import com.bootcamp.banca.entity.Enterprise;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Document("creditEnterprise")
public class CreditEnterprise {
	private String _id;
	private Enterprise enterprise;
	private List<Credit> credits;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date modifiedAt;

	public CreditEnterprise(Enterprise enterprise, List<Credit> credits, Date createdAt) {
		this.enterprise = enterprise;
		this.credits = credits;
		this.createdAt = createdAt;
	}
}