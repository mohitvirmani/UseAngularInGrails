package com.UseAngularInGrails

class Ingredients {

	double amount
	
	String amountUnits
	
	String ingredientName
	
	static belongsTo=[recipies:Recipies]
	
    static constraints = {
		amount nullable:true
		amountUnits nullable:true
		ingredientName nullable:true
    }
}
