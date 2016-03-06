# Basic Architecture #

The project is divided into 3 modules,
  1. Business -> handles business logic + core back end
  1. Loader logic -> handles RPC between business and the web front end + Session access
  1. Loader Web -> the web front end

## Business ##
### Entities ###
The module uses the Dbgate library to handle the database mappings with the entities. On top of Dbgate the module defines a super entity based on the interface IEntity so that it would seamlessly support vertical properties for any given object. And there is IEntityKey interface to represent the primary key of the entity.
It all boils down to GSEntity class, which will be the super class for the entities. readProperty() and writeProperty() will get vertical values in place against any GSEntity. However for that it's need to define property type against entity type with the help of GSEntityType and GSEntityPropertyType entities. Refer to User entity for more details on how to create an entity.
### Repositories ###
This is like any classic repository where any operation related to entity is done via a repository. The repository is supported by transactions where transaction need to manually attach to the repository if there are multiple repositories are used within a single transaction. Repository need to be created as a subclass of AbstractRepository. An example would be UserRepository.
### Tests ###
The tests against the Business layer is created to cover as much as code possible with very less effort. All the tests regarding entities and repositories are handled by !DBClassTests and RepositoryTests classes, while each entity tests need to be sub classed from DbClassTestBase and each repository test need to be sub classed by RepositoryTestBase. For more information refer to classes UserTestBase and UserRepositoryTestBase. Once a new test class added it has to be registered in !DBClassesTests or RepositoryTests based on test type.

## Loader Logic ##
This is classic GWT RPC services are used. This section is used as two different GWT modules so that this section can be keep in a separate place from the web front end. Refer to Auth.gwt.xml or Workspace.gwt.xml.
Any service is defined as subclasses of AbstractService where authentication is checked, logging is handled and Database Connection is initialized. Refer to AuthenticationServiceImpl for details.
There is a special service for CRUD operations defined as !ICRUDService where basic crud operations are supported, with easy extensibility. Refer to UserServiceImpl and !CRUDServiceImpl.

## Loader Web ##
This is the GWT front end. It's based on MVC architecture IModel, IView and IController. Views displayed in the main container is usually derived from MasterChildView or if it's CRUD operation it should be the CRUD view.

###License
GNU GPL V3



