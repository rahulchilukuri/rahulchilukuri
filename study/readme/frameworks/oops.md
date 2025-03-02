# Basic OOP Concepts
What is Object-Oriented Programming (OOP)?
OOP is a programming paradigm that organizes software design around objects and classes, focusing on encapsulation, inheritance, polymorphism, and abstraction.

# What are the four pillars of OOP?

Encapsulation: Bundling data and methods that operate on the data within a single unit (class).

Inheritance: A mechanism where a new class derives properties and behaviors from an existing class.

Polymorphism: The ability of an object to take many forms (e.g., method overriding and overloading).

Abstraction: Hiding complex implementation details and showing only essential features.

# Qs
What is a class?
A class is a blueprint or template for creating objects. It defines properties (attributes) and behaviors (methods) that the objects will have.

What is an object?
An object is an instance of a class. It represents a real-world entity with a state (data) and behavior (methods).

What is the difference between a class and an object?
A class is a blueprint, while an object is an instance of that blueprint.

What is encapsulation?
Encapsulation is the concept of bundling data (attributes) and methods (functions) that operate on the data into a single unit (class) and restricting access to some of the object's components.

What is abstraction?
Abstraction is the process of hiding complex implementation details and exposing only the necessary features of an object.

What is inheritance?
Inheritance allows a class (child/derived class) to inherit properties and behaviors (methods) from another class (parent/base class).

What is polymorphism?
Polymorphism allows objects of different classes to be treated as objects of a common superclass. It can be achieved through method overriding and method overloading.

What is the difference between method overloading and method overriding?

Method Overloading: Multiple methods in the same class with the same name but different parameters.

Method Overriding: A subclass provides a specific implementation of a method already defined in its superclass.

Intermediate OOP Concepts
What is a constructor?
A constructor is a special method in a class that is automatically called when an object is created. It initializes the object's state.

What is a destructor?
A destructor is a special method that is automatically called when an object is destroyed or goes out of scope.

What is the difference between a constructor and a method?
A constructor is used to initialize an object, while a method is used to perform operations on the object.

What is the this keyword?
The this keyword refers to the current instance of the class. It is used to access class members (attributes and methods).

What is the super keyword?
The super keyword is used to refer to the parent class. It is often used to call the parent class's constructor or methods.

What is a static method?
A static method belongs to the class rather than an instance of the class. It can be called without creating an object.

What is the difference between static and instance methods?

Static methods belong to the class and are called using the class name.

Instance methods belong to an object and are called using an object.

What is an interface?
An interface is a blueprint of a class that defines a set of methods that a class must implement. It supports multiple inheritance.

What is an abstract class?
An abstract class is a class that cannot be instantiated. It is used as a base class and can contain abstract methods (methods without implementation).

What is the difference between an abstract class and an interface?

An abstract class can have both abstract and concrete methods, while an interface can only have abstract methods (in most languages).

A class can inherit only one abstract class but can implement multiple interfaces.

What is composition in OOP?
Composition is a design principle where a class is composed of one or more objects of other classes. It represents a "has-a" relationship.

What is aggregation?
Aggregation is a type of association where one class contains a reference to another class, but the contained class can exist independently.

What is association in OOP?
Association is a relationship between two classes where one class uses or interacts with another class.

What is coupling?
Coupling refers to the degree of dependency between classes. Low coupling is preferred for better maintainability.

What is cohesion?
Cohesion refers to how closely the responsibilities of a class or module are related. High cohesion is preferred for better design.

Advanced OOP Concepts
What is a singleton class?
A singleton class ensures that only one instance of the class is created and provides a global point of access to it.

What is a factory pattern?
The factory pattern is a creational design pattern that provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects created.

What is a design pattern?
A design pattern is a reusable solution to a commonly occurring problem in software design.

What is the difference between deep copy and shallow copy?

Shallow Copy: Copies only the reference to the object, not the actual object.

Deep Copy: Creates a new object and copies all the data from the original object.

What is method chaining?
Method chaining is a technique where multiple methods are called on the same object in a single statement.

What is the Liskov Substitution Principle (LSP)?
LSP states that objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program.

What is the Open/Closed Principle?
The Open/Closed Principle states that software entities (classes, modules, functions) should be open for extension but closed for modification.

What is the Dependency Inversion Principle?
The Dependency Inversion Principle states that high-level modules should not depend on low-level modules. Both should depend on abstractions.

What is the Interface Segregation Principle?
The Interface Segregation Principle states that a class should not be forced to implement interfaces it does not use.

What is the Single Responsibility Principle?
The Single Responsibility Principle states that a class should have only one reason to change, meaning it should have only one responsibility.

OOP in Specific Languages
What is the difference between public, private, and protected access modifiers?

Public: Accessible from anywhere.

Private: Accessible only within the class.

Protected: Accessible within the class and its subclasses.

What is a virtual function?
A virtual function is a function in a base class that can be overridden in a derived class. It enables runtime polymorphism.

What is a pure virtual function?
A pure virtual function is a virtual function with no implementation in the base class. It makes the class abstract.

What is a friend function?
A friend function is a function that is not a member of a class but has access to its private and protected members.

What is multiple inheritance?
Multiple inheritance is a feature where a class can inherit from more than one base class.

What is a namespace?
A namespace is a container for a set of identifiers (e.g., classes, functions) to avoid naming conflicts.

What is operator overloading?
Operator overloading allows defining custom behavior for operators (e.g., +, -) when used with user-defined types.

What is a template in C++?
A template is a feature in C++ that allows functions or classes to operate with generic types.

What is a lambda function?
A lambda function is an anonymous function that can be defined inline.

What is garbage collection?
Garbage collection is the automatic process of reclaiming memory by deleting objects that are no longer in use.

Scenario-Based Questions
How would you design a system for a library management system using OOP?

Classes: Book, Member, Library, Transaction.

Relationships: Library has Books, Member borrows Book, Transaction records the borrowing.

How would you handle a situation where multiple classes share common functionality?
Use inheritance to create a base class with the common functionality and derive the other classes from it.

What is the difference between is-a and has-a relationships?

is-a: Represents inheritance (e.g., a car is a vehicle).

has-a: Represents composition (e.g., a car has an engine).

How would you implement a singleton class in Java?

java
Copy
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
What is the difference between compile-time polymorphism and runtime polymorphism?

Compile-time Polymorphism: Achieved through method overloading (resolved at compile time).

Runtime Polymorphism: Achieved through method overriding (resolved at runtime).