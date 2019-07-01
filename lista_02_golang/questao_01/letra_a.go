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
  fmt.Println("int gerado: ", num)
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
  chanResposta := make(chan int, 1)

  chanResposta <-gateway(4)

  close(chanResposta)

  fmt.Println("a primeira a finalizar foi: ", <-chanResposta)
}
