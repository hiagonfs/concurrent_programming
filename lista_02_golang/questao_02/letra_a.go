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

func gateway(num_replicas int) int {	

    messages := make(chan int)
    for i := 0; i < num_replicas; i++ {
        go func() {
           messages <- request() 
        }()
    }

	msg := <- messages
	
	return msg
}

func main() {
	
	chanResposta1 := make(chan int, 1)
	chanResposta2 := time.Tick(8000 * time.Millisecond)
	
	chanResposta1 <-gateway(3) 

	select {
		
		case result := <-chanResposta1:
			fmt.Printf("Retorno %d", result) 
			
		case <-chanResposta2:
			fmt.Println("-1") 
	}
}