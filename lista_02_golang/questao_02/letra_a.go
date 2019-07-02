package main

import (
"fmt"
"time"
"math/rand"
)

func request() int {	
  rand.Seed(time.Now().UnixNano())
  min := 1
  max := 30
  num := rand.Intn(max - min) + min
  fmt.Println(num)
  time.Sleep(time.Duration(num) * time.Second)
  return num 
}

func gateway(num_replicas int) chan int {	

    messages := make(chan int)
    for i := 0; i < num_replicas; i++ {
        go func() {
           messages <- request() 
        }()
    }

	return messages
}

func main() {
	
	timer := time.Tick(8 * time.Second)
	
	select {
		
		case result := <-gateway(2):
			fmt.Printf("Retorno %d", result) 
			
		case <-timer:
			fmt.Println("-1") 
	}
}
