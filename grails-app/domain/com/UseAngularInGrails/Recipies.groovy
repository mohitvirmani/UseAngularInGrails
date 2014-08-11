package com.UseAngularInGrails

class Recipies {

	String name

	String description

	String instructions

	static hasMany=[ingredients : Ingredients]


	static constraints = {
		name nullable:true
		description nullable:true
		instructions nullable:true
	}
}
