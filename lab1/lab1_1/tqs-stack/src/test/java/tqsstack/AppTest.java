package tqsstack;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class AppTest { 

        protected App stack;
        protected App boundedStack;
        public static int LIMIT = 3;
    
        
        @Before
        public void init() {
            stack = new App();
        }
    
        @Test
        @DisplayName("A stack is empty on construction")
        public void stackIsEmptyTest() {
            assertTrue(stack.isEmpty());
        }
    
        @Test
        @DisplayName("A stack has size 0 on construction")
        public void stackSizeZeroOnConstructionTest() {
            assertEquals(0, stack.size());
        }
    
        @Test
        @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
        public void stackCorrectSizeAfterPushTest() {
            int testingSize = 3;
            for(int i = 1; i <= testingSize; i++) {
                stack.push(i);
            }
            
            assertFalse(stack.isEmpty());
            assertEquals(testingSize, stack.size());
        }
    
        @Test
        @DisplayName("If one pushes x then pops, the value popped is x")
        public void valuePushedIsPoppedTest() {
            int testingVal = 0;
            stack.push(testingVal);
    
            assertEquals(testingVal, stack.pop());
        }
    
        @Test
        @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
        public void stackSameSizeEqualAfterPeekTest() {
            int testingVal = 0;
            stack.push(testingVal);
            int testingSize = stack.size();
    
            assertEquals(testingVal, stack.peek());
            assertEquals(testingSize, stack.size());
        }
    
        @Test
        @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
        public void afterPopsStackIsEmptyTest() {
            int testingSize = 3;
            for(int i = 1; i<=testingSize; i++) {
                stack.push(i);
            }
            for(int i = 1; i<=testingSize; i++) {
                stack.pop();
            }
    
            assertTrue(stack.isEmpty());
            assertEquals(0, stack.size());
        }
    
        @Test
        @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
        public void poppingEmptyStackExceptionTest() {
            assertTrue(stack.isEmpty());
            NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
                stack.pop();
            });
            assertEquals("Popped empty stack", thrown.getMessage());
        }
    
        @Test
        @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
        public void peekingEmptyStackExceptionTest() {
            assertTrue(stack.isEmpty());
            NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
                stack.peek();
            });
            assertEquals("Peeked empty stack", thrown.getMessage());
        }
    
    
        @Before
        public void boundedStackInit() {
            boundedStack = new App(LIMIT);
        }
    
        @Test(expected = IllegalStateException.class)
        @DisplayName("For bounded stacks only:pushing onto a full stack does throw an IllegalStateException")
        public void boundedStackExceptionTest() {
    
            for(int i=0; i<LIMIT; i++) {
                boundedStack.push(i);
            }
            boundedStack.push(0);
        } 

}