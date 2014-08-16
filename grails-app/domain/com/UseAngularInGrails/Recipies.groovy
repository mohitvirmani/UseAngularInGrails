package com.UseAngularInGrails

class Recipies {

	String name

	String description

	String instructions

	String pic
	
	String picpath
	
	static hasMany=[ingredients : Ingredients]


	static constraints = {
		name nullable:true
		picpath nullable:true
		pic nullable:true
		description nullable:true
		instructions nullable:true
	}
}
