package example

import grails.gorm.MultiTenant

class Vehicle implements MultiTenant<Vehicle> { // <1>
    String model
    Integer year

    static hasMany = [engines: Engine]
    static constraints = {
        model blank:false,unique: true//make vehicle model unique
        year min:1980
    }
}
