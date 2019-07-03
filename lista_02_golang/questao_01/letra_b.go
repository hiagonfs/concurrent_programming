package main

import (
"fmt"
"time"
"math/rand"
"sync"
)

func sum_channel(ch chan int) int {
	total := 0
	for val := range ch {
		total += val
	}
	return total
}

func request() int {	
  rand.Seed(time.Now().UnixNano())
  min := 1
  max := 30
  num := rand.Intn(max - min) + min
  fmt.Println("int gerado: ", num)
  //time.Sleep(time.Duration(num) * time.Second)
  // deixei o sleep comentado por questoes de agilidade na soma
  return num 
}

func gateway(num_replicas int) int {
	 
    messages := make(chan int) // canal de entrada de elementos
    sum := make(chan int, num_replicas) // canal de saida da soma

	go func() {
		for i := 0; i < num_replicas; i++ {
        	 messages <- request()
		   	 
        }
		close(messages)
    }()
	
	var wg sync.WaitGroup
	wg.Add(num_replicas)
	
	for i := 0; i < num_replicas; i++ {
		go func() {
			sum <- sum_channel(messages)
			wg.Done()
		}()
	}

	wg.Wait()
	close(sum)
	return sum_channel(sum)
}

func main() {

  result := gateway(5)

  fmt.Println("a soma dos inteiros gerados eh: ", result)

}
