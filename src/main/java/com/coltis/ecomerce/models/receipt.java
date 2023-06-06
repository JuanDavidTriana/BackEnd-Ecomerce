package com.coltis.ecomerce.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="receipts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class receipt {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="receipt_number")
	private Integer receiptNumer;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="created_By")
	private user user;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="Items_On_Receipt",
			joinColumns = {@JoinColumn(name="receiptNumer")},
			inverseJoinColumns = {@JoinColumn(name="ItemId")}
	)
	private List<product> products;
	
	private Double total;
	
	@Column(name="created_Date/Time")
	private String dateTime;
	
	private Integer amountOfItems;
	
	public receipt(user user, List<product> products, Double total, String dateTime, Integer amountOfItems) {
		this.user = user;
		this.products = products;
		this.total = total;
		this.dateTime = dateTime;
		this.amountOfItems = amountOfItems;
	}
	
}
