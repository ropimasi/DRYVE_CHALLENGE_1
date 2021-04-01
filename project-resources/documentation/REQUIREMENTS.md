# REQUIREMENTS - DRYVE CHALLENGE 1  
  
*Start 202103301948-UTC/GMT/Z-time*  
*Last change 202103311230-UTC/GMT/Z-time*  
  
## RECRUITMENT REQUIREMENTS
* Spring Boot with Java or Kotlin; ![Passed](../images/brush-right-64-64.png "Passed")
* Maven or Gradle; ![Passed](../images/brush-right-64-64.png "Passed")
* Relational database; ![Passed](../images/brush-right-64-64.png "Passed")
* REST APIs for registration and searching. ![Passed](../images/brush-right-64-64.png "Passed")
  
  
## PLUS RECRUITMENT RECRUITMENTS
* Unitary tests; ![Fail](../images/brush-wrong-64-64.png "Fail")
* Integration tests; ![Passed](../images/brush-right-64-64.png "Passed")
* Database versioning (flyway or liquibase); ![In Progress](../images/brush-inprogress-64-64.png "In Progress")
* Dockerfile for project's container; ![Fail](../images/brush-wrong-64-64.png "Fail")
* Docker compose for project dependencies; ![Fail](../images/brush-wrong-64-64.png "Fail")
* Publication of registration events using RabbitMQ. ![Fail](../images/brush-wrong-64-64.png "Fail")
  
  
## BUSINESS REQUIREMENTS
### User gives information to registration
* **Plate** must be unique, that is, it can be only one record with each **plate value**, it cannot be duplicated;
  > Analysis:
  >  + `VehicleEntity` will be an *entity class* in Java code, and `vehicle` will be an *entity table* on DB;
  >  + Plate's value will be, according to current Mercosul's regulations, evaluated with letters and numbers in an 8 characters combination;
  >    - `plate` will be an `String` attribute of `VehicleEntity` class in Java code, and `plate` will be an `character varing` attribute of `vehicle` table on DB.
* **Advertising Price** must be a montary value;
  > Analysis:
  >  + Advertising Price's value, obviously, need to be grater than "0.00";
  >  + `priceAdv` will be an `BigDecimal` attribute of `VehicleEntity` class in Java code, and `price_adv` will be an `numeric(12,2)` attribute of `vehicle` table on DB.

/* HERE: about model year relation plural or singular, business and/or technique. */
* **Model-Year-Relation** 
  > FK on APP.DB.modelyear table, according APP's verification below.
  + ModelId (ex: ‘5bc16064-d3ee-4aed-a264-a914233d0c4f’)
    > APP verify if exists this model and below year together on APP.DB.modelyear.modelid table.
  + VehicleYear
    > APP verify if exists this year and above model together on APP.DB.modelyear.year table.
  
  
## TECHNICAL REQUIREMENTS
* **Brand** must have an `id` attribute preferably typed as `UUID` (ex: "ca43ec74-5bb0-4288-ab11-5df094ca4dc4");
  > Analysis:
  >  + `BrandEntity` will be an *entity class* in Java code, and `brand` will be an *entity table* on DB;
  >  + Although would be possible making an attribute `name` as the identifier and primary key in this entity, basing on the concept all vehicle brands have their names uniques to order to copyrights rules, this project it will be adopting a different attribute as object/record-identifier, up-to-now, allowing 2 or more brands with the same name, maybe to attempt external-client applications' needs:
  >    - `id` will be an `UUID` attribute of `BrandEntity` class in Java code, and `id` will be an `uuid`, primary key, attribute of `brand` table on DB;
  >    - `id` will be used to object-association to `ModelEntity` class in Java code, and `id` will be foreign key to `model` table on DB.
* **Model** must have an `id` attribute preferably typed as `UUID` (ex: "ca43ec74-5bb0-4288-ab11-5df094ca4dc4");
  > Analysis:
  >  + `ModelEntity` will be an *entity class* in Java code, and `model` will be an *entity table* on DB;
  >  + `id` will be an `UUID` attribute of `BrandEntity` class in Java code, and  `id` will be an `uuid`, primary key, attribute of `models` table on DB;
  >  + `id` will be used to object-association to `ModelYearEntity` in Java code, and to `models` on DB.
  
  
### API performs external search
Get the vehicle's price on KBB table https://6048bdf1fb5dcc0017968e3f.mockapi.io/api/v1/kbb{/.../...}
Ex: https://6048bdf1fb5dcc0017968e3f.mockapi.io/api/v1/kbb/prices/1
  
  
### APP saves every vehicle data on relational DB
  Plate			/* given from user */
  AdvertisingPrice	/* given from user */
  modelyearId		/* APP attributes this FK according business rules pointed above */
	VehicleYear		/* gotten from APP.DB.modelyear.year table if the given year from user exists on AP.DB.modelyear table */
	/* models and brand also is registered */
  KBBPrice		/* gotten from external api */
  RegistryDate		/* gotten form system */
  
  
### Brands, Models, and Vehicles (at least) must have a FK chain between them.
Ex: I'll do Brands, Models, modelyear-Relation and Vehicles.
  
  
### Plate is UNIQUE in Vehicles.
  
  
### It is mandatory all vehicle registering must be related to an existing brand, model, and year pre-registered on API-DB and KBB-API.
  
  
### The result of a vehicle searching on this APP must follow the template:
{
  "Plate": "XYZ-1234",
  "AdvertisingPrice": 1234.56,
  "VehicleYear": 2020,
  "KBBPrice": 120000.00,
  "RegistryDate": "2020-05-18",
  "Brand": "FIAT",
  "Model": "UNO"
}  
  
  