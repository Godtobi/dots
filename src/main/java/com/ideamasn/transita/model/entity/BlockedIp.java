package com.ideamasn.transita.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class BlockedIp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String ip;

	@Column(nullable = false)
	private Long requestNumber;

	@Column(nullable = false)
	private String comment;

	@CreationTimestamp
	private Date createdAt;

}
