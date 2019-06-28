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
    msg := <-messages
    return msg
}


func main() {

  fmt.Println(gateway(3))
}
