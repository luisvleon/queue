/*------------------------------------------------------------------------
#Name: Luis Leon
#Student ID: 
#COP3530- Data Structures
#Fall 2020 – We 6:00 PM - 7:40 PM
#Assignment # 5 - Circular queue
*/

public class Queue
{
	// data members variables
	int front;          		// store the front position
	int rear;           		// store the rear position
	boolean lastOperationIsPush;    // last operation was put iff lastOperationPush is true or false
	int capacity;					//stores the queue capacity
	Object [] queue;    			// elements array

   // constructor for the queue
   public Queue(int cap)
   {
      if (cap < 1) 
      {
    	  //if the capacity is less than 1 does noting
    	  System.out.println("initialCapacity must be >= 1");
      }         
      else
      {
    	  //Instantiate and Sets the capacity
    	  queue = new Object [cap];
    	  capacity = cap;
    	  //Sets front and rear variables 
    	  front = 0;
    	  rear = 0;
      }
   }
   
   //Method to determine if the queue is full
   public boolean isFull()
   {
	   // if front is equals to rear AND the last operation was a push or enque
	   return (front == rear && lastOperationIsPush);
   }
   
   //Method to determine if the queue is empty
   public boolean isEmpty()
   {
	// if front is equals to rear AND the last operation was a pop or deenque
	   return (front == rear && !lastOperationIsPush);
   }  
   
   public void delete(int k)
   {
	   //Check if the queue element is not null
	   if (queue[k] != null) 
	   {
		   //Store the variable for the while loop to swap the elements
		   int next = 0;	
		   //Sets the variable to false if is a dequeue or delete operation
		   lastOperationIsPush = false;
		   //before set to null the element copy the content to display
		   Object x = queue[(front + k)% capacity];
		   //sends to null the selected element
		   queue[(front + k)% capacity] = null;
		   next = k + 1;	//increase the next variable to start swapping elements to the left
		   //next++;
		   //move the elements to the left checking if there are more on the right
		   while(next < queue.length && queue[next] != null)
		   {
			  queue[next - 1] = queue[next];	//Copies the content to the previous position
			  queue[next] = null;				//set to null the actual element
			  next++;							//increase the counter
			  rear--; 		//if the rear value is different than 0, decrease the value
		   }	    		  
		   
		   rear--;  //finally retry 1 the rear position
		   System.out.println("Deleted value: k=" + k + "\tValue: " + x + " Front position: " + front + "\tRear position: " + rear);        	  
	   }
   }

   //Insert an element at the right of the queue
   public void enque(Object data) throws QueueFullException
   {    	 	   
	   if(isFull())		//if the queue is full throws an error exception
	   {			   
		   try			//The exception is handled 
		   {
			   throw new QueueFullException("Cannot enque " + data + " The queue is full");
		   }
		   catch  (QueueFullException e)
		   {
			   //output to console the error message
			   System.out.println(e.getMessage());
		   }    	  
	   }
	   else
	   {
		   queue[rear] = data;						//Record data first with previous value to start at 0
		   if(rear == queue.length) rear = 0;		//If the queue reach its end go to 0 
		   rear = (rear + 1) % queue.length;		//Locate the rear location   
		   lastOperationIsPush = true;				//sets the variable to true 
			   
		   //Output the results
		   System.out.println("Enqued value:  " + data + " \tFront position: " + front + " \t\tRear position: " + rear);			   
	   }		   
   }   

   //method to dequeue elements from the left of the array
   public void dequeue() throws QueueEmptyException
   {	  
      if (!isEmpty()) 					//check if the queue is not empty to proceed
      {    	  
    	  Object x = queue[front];		//get the value before dequeue
    	  queue[front] = null;			//set to null the element at the left of the queue
    	  front = (front+1) % queue.length;
    	  lastOperationIsPush = false;	//sets the variable to false
    	  //front--;						//increase the front value
    	  
    	  //output the result
    	  System.out.println("Dequeued value: " + x + " \tFront: " + front + " \t\t\tRear position: " + rear);        	  
      }
      else
      {
    	  try	
    	  {		//if the queue is empty throw the empty exception
    		  	throw new QueueEmptyException("\nCan't dequeue, queue is Empty \n");
    	  }
    	  catch  (QueueEmptyException e)
    	  {	  	//output to console the error message
    		  	System.out.println(e.getMessage());
    	  }    	  
      }      
   }
   
   //Class to handle queue full exceptions
   class QueueFullException extends RuntimeException{
	   //Set a default version ID
	   private static final long serialVersionUID = 1L;
	   //Receive the error message
	   public QueueFullException(String message)
	   {
		   super(message);
	   }
	}
   //Class to handle queue empty exceptions
	class QueueEmptyException extends RuntimeException{
		//Set default version ID
		private static final long serialVersionUID = 1L;
		//Receive the error message
		public QueueEmptyException(String message)
		{
			super(message);
		}
	}	
	
	 public void status()
	 {	   
		 //Construct the string
		 String str = "Queue status: \t| ";	  
		 //iterate the queue
		 for(int i = 0; i < queue.length; i++)
		 {
			 //add the value to the resulting string
			 str += queue[i] + " | ";
		 }
		 System.out.println("\t" + str + "\n");  	
	   }
	 
	 public void formula()
	 {
		 System.out.println("Formula:");
		 System.out.println("No. Elements (" + capacity + ") = " + "( " + capacity + " + " + rear + " - " + front + ") % " + capacity + " = " + ((capacity + rear - front) % capacity) +" \n");
	 }
   
   //Driver class to demonstrate the usage of the Queue class
   public static void main(String[] args) 
   {
	   //Sets the capacity
  	   int cap = 6;  	    
  	    
  	   //Instantiate the queue object
  	   Queue queue = new Queue(cap);   	
   	
  	   System.out.println("The queue capacity is: " + cap + "\n");
  	   System.out.println("Formula in terms of the Array Capacity, front and Rear, for the number of elements in the list.");
  	   System.out.println("No. Elements (capacity) = (( capacity + rear - front ) mod capacity)");
  	   
  	   queue.dequeue();			//Testing underflow
   	
  	   queue.enque(1);			//Normal enqueue operation
  	   queue.status();			//Print status to observe behavior
  	   queue.enque(2);
  	   queue.status();
  	   queue.enque(3);
  	   queue.status();
  	   
  	   queue.formula();			//Applying the formula
  	   
  	   queue.dequeue();			//Testing dequeue operation
  	   queue.status(); 
  	   
  	   queue.delete(1);			//Testing delete operation
  	   queue.status(); 
  	   queue.enque(1);
  	   queue.status(); 
  	   queue.enque(4);
  	   queue.status();	  	   
  	   queue.enque(5);
  	   queue.status();
  	   queue.enque(6);
  	   queue.status();
  	   
  	   queue.formula();			//Applying the formula
  	   
  	   queue.enque(7);
  	   queue.status();
  	   queue.enque(8);			//Testing overflow
  	   queue.status();  	   
   }
}
