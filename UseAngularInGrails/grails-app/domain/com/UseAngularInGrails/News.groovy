package com.UseAngularInGrails

class News {

	String heading
	String descripton
	String pic
	String picpath

	static constraints = {
		heading size: 0..100,nullable:true
		descripton size: 0..10000,nullable:true
		pic nullable:true
		picpath nullable:true
	}
}
