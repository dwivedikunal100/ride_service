## Problem statement

### Ride Hailing Service Backend: Machine Coding Problem

We need to build a backend for the ride hailing platform like Uber. Come up with working runnable code for it.

Requirements:

- Register a user
- Register a driver
- Book a ride if any driver is available within a certain radius. Think about what kind of flow you want to implement.
- Complete a ride and get the cost for it.
- Update location of a cab.
- Implement a tiered pricing strategy: one of the examples could be -
    - Minimum ride price - 50 rs
    - First 2 KMs at 10 Rs / KM
    - 3-5 KMs at 8 Rs / KM
    - 6 KM and beyond at 5 rs / KM

- Show ride history for a user: ongoing and completed rides.
- Show ride history for a driver: ongoing and completed rides.
- Add support for different types of cars.
    - Sedan and Hatchback cars.
    - Different pricing rates for hatchback and sedan.
- When hailing a cab, if hatchback is not available upgrade the user to a sedan at no extra cost.
- Add support for coupon codes.
    - Add a coupon code
    - Delete a coupon code
- Apply a valid coupon code when starting a ride to get it at the discounted price.

## Assumptions

### General

1. Map is a 2-D matrix, each user/driver location is [x_coordinate,y_coordinate] all positive.
2. 1 Unit = 1 KM
3. Both User and Driver when registering have to add default location
4. All prices are in INR

### User

1. There is no additional metadata associated with user, user is identified by userId which is string

### Driver

1. There is no additional metadata associated with driver, driver is identified by driverId which is string

## Class Diagrams

## Controllers

![](https://lh6.googleusercontent.com/JRX38sJpFakt9DOGVw6Z-vrtCFnGv68PgxbdQRzqjFR2NmijmILwzA_dsh3Rv-lNZvqi4HGKSIskK6C2pRKQGzoL97_Ai2OFqbsFkZja8SmlhD_5lSjq-IgzT0qKrnLhnXGZoOIH)

## Models

![](https://lh6.googleusercontent.com/v13nsGAvy_uHeJCOzm9Wd6Uw78tfHWOLC336-Ia-YrQn459YCl9I0sRofe_TS7Jg8gaVbL4vHL5wWBK3mYCgs2CUuVCYF9ICiO6Vz_fD9eFRMv_qeByb6AUin3RthlURHJ4fInGn)

## Sequence Diagrams

![](https://lh4.googleusercontent.com/s9sDCzUSFJYRK1sn9_y3X9hN5peMqdsfrCl14kqq1_fuNkVO4nKFUxJ6AqzYIBoechm2lUtapd7IAmBLC7hySPauvFyR0GYZ8woxGLtIrmmCa_s4PMxqGYhCuiPdGmepdkphtLJd)

![](https://lh5.googleusercontent.com/KIqP6IrMgmRW6U5ubWsRVYXmScCAyQPafp_AVAYx6RuA-CuoKby4McCRk7jwMME7NaArxxEjmdpJ3LFSPeG18JRJg83VgrOByA4B670ELYu4GfhcA_yUso_hilD1gpBSGVrtvVEI)

![](https://lh6.googleusercontent.com/nMUcqNVkQV-aF5RBSVVfK2rtO-pqAdgi2FT1YFkspMM8iw8_u6_lIeeRYYDQepg9EMuIIR4BRCtw1mu20ZBViqnWNfhwRqF2ionBAnTk_o7ArjToNdyIHRdpLI8A0rwlAlzvqRwn)

## Design Decisions/ What was not covered

1. Trips history is stored as part of the user /driver object since querying and filtering the trip database will be
   costly.
2. Adding to the database would have taken more time, hence in-memory map is used.
3. Tests for trips were not added, given more time all cases would have been covered.
4. Controllers should not have logic, given more time all they could have moved to the processor following the single
   responsibility principle.
5. Flow for user taking ride can be improved instead of user selecting particular driver, user should be able to only
   select a car(Sedan/ Hatchback) and in case HatchBack was available during findRide but not during start ride, user
   should be upgraded to Sedan with Hatchback’s money

## Extensibility

1. Strategies can be extended based on state, country, etc as needed which includes both changing base rate, etc.
2. CarType can be extended to use different rates per KM.
3. Location can be changed to real world GeoLocation easily since the calculation is separated from other business
   logic.
4. The current structure can use both SQL and Non-SQL database.

## Points to Note:

Feel free to use whatever technologies you're the most comfortable with. This includes any sort of open-source
third-party frameworks.

Your priorities should be:

- Workable solution (Shortcuts are fine, but be prepared to justify them and explain better solutions you would have
  implemented with more time)
- Clean Code & Design (How code is divided into proper readable separations)
- Extensibility of the solution (How easily can it solve for some other strategies to achieve similar / better outcome)

Using a database is optional. You can store data in-memory but modeling should be complete.
You have an option to build a CLI app or an API based web app.

Also, plan to give a 30 minutes demo on your submission.

- 10 minutes - Showing your working solution in action and how it performs
- 10 minutes - Explaining approach and walking through the code
- 10 minutes - Talking about extensibility, other use cases or ways to solve this problem better and Q&A

Please reach out if you are stuck anywhere or need any help. We want this to be an enjoyable experience for you.

  
