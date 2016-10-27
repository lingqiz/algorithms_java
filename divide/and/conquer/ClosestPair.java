package divide.and.conquer;
public class ClosestPair 
{
	/*
	 * High level description for solving closest pair problem 
	 *	- Sort points w.r.t. x and y coordinate -> Px, Py
	 *	- Primitives : operations with no cost 
	 *
	 *	- Divide into smaller problem
	 *		divide Px into left half and right half  
	 *
	 *  - Conquer 
	 *		find closest point in each half, get smallest distance delta 
	 *		
	 *	- Combine Result 
	 *		two cases : 
	 *			- in left / right half √
	 *			- split closest 
	 *		-> find split closest point in O(n) time 
	 *		Two main claim :
	 *			- filter by x_mid +- delta, sorted by y coordinate 
	 *			- within 7 indexes away 
	 */
	
	
	
}
