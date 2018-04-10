package com.example.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "Trusts")
public class TrustRelationship {

	@Id @GeneratedValue private Long id;
	
	private int weight;
	
	@EndNode
	private User trustee;
	@StartNode
	private User truster;

	@SuppressWarnings("unused")
	private TrustRelationship() {
		// Empty constructor required as of Neo4j API 2.0.5
	};
	
	/**
	 * @param weight
	 * @param trustee
	 */
	public TrustRelationship(int weight, User trustee, User truster) {
		super();
		this.weight = weight;
		this.trustee = trustee;
		this.truster = truster;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the trustee
	 */
	public User getTrustee() {
		return trustee;
	}

	/**
	 * @param trustee the trustee to set
	 */
	public void setTrustee(User trustee) {
		this.trustee = trustee;
	}

	/**
	 * @return the truster
	 */
	public User getTruster() {
		return truster;
	}

	/**
	 * @param truster the truster to set
	 */
	public void setTruster(User truster) {
		this.truster = truster;
	}
	
}
