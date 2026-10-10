# Design Notes

## Why ArrayList Instead of Array?

We chose `ArrayList` over plain arrays for storing Students, Courses, and Enrollments for several reasons:

1. **Dynamic sizing**: Arrays in Java have a fixed size that must be declared at creation time. Since we don't know in advance how many students or courses will be added, we would need to manage resizing manually with arrays. `ArrayList` handles this automatically — it grows and shrinks as elements are added or removed.

2. **Built-in methods**: `ArrayList` provides convenient methods like `add()`, `remove()`, `get()`, and `size()` that simplify data management. With arrays, we would need to write additional logic for inserting at positions, shifting elements on removal, and tracking the current count of elements.

3. **Type safety with generics**: Using `ArrayList<Student>` ensures that only `Student` objects can be stored in the list, catching type errors at compile time rather than runtime.

## Where Static Members Are Used and Why

Static members are used in the following places:

- **IdGenerator**: The `studentIdCounter`, `courseIdCounter`, and `enrollmentIdCounter` fields are `static` because IDs need to be unique across all instances. A static counter belongs to the class itself rather than any individual object, so every call to `getNextStudentId()` increments the same shared counter regardless of where it's called from.

- **InputValidator**: All validation methods (`validateNotEmpty`, `parsePositiveInt`, `parseMenuOption`) are `static` because they are pure utility methods — they don't depend on any instance state. Making them static allows them to be called directly on the class without creating an object.

- **MenuOptions and AppConstants**: All fields are `static final` because they represent constant values shared across the application. There's no need to instantiate these classes.

## Where Inheritance Is Used and What We Gained

We used inheritance in the entity layer:

- **Person** is the base class with common fields: `id`, `firstName`, `lastName`, `email`.
- **Student** extends `Person` and adds `batch` and `active` fields.

What we gained:

1. **Code reuse**: Common person-related fields and their getters/setters are defined once in `Person` and inherited by `Student`. If we add a `Trainer` class later, it inherits the same fields without duplication.

2. **Polymorphism**: The `getDisplayName()` method is defined in `Person` and overridden in `Student` to include batch information. This demonstrates method overriding — calling `getDisplayName()` on a `Student` object produces specialized output while the method signature stays the same.

3. **Constructor chaining**: `Student` constructors use `super()` to call `Person`'s constructor, demonstrating how child classes build on parent initialization.

## Architecture Decisions

### Layered Structure

The project follows a layered architecture:

- **entity/** — Data classes representing domain objects (Student, Course, Enrollment)
- **repository/** — Data storage layer using ArrayList (in-memory storage)
- **service/** — Business logic layer that coordinates between UI and repository
- **constants/** and **enums/** — Application-wide fixed values
- **exception/** — Custom exceptions for clean error handling
- **util/** — Utility classes (ID generation, input validation)
- **Main.java** — Console UI handling menus, user input, and calling service methods

This separation keeps each layer focused on a single responsibility. The UI layer (`Main.java`) only handles displaying menus and reading input. The service layer contains the business rules. The repository layer manages data storage. This makes the code easier to understand, test, and extend.
