package example

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.gorm.multitenancy.Tenants
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

@CompileStatic
class Application extends GrailsAutoConfiguration implements ApplicationRunner { // <1>

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    @Transactional
    void run(ApplicationArguments args) throws Exception { // <2>
//        Manufacturer.saveAll( // <3>
//                new Manufacturer(name: 'Audi'),
//                new Manufacturer(name: 'Ford')
//        )


        Tenants.eachTenant { t ->
            //at this point we are only interested in the tenantDB..
            //however the unique validator uses the default datasource to validate this object so it will fail with table not found
            println("Tenant $t")
            new Vehicle(model: "$t - Explorer", year: 2017).save(failOnError: true, flush: true)

        }
    }
}
