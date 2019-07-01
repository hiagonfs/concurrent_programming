package main

import (
	"fmt"
	"os"
	"runtime"
	"strconv"
	"sync"
)

func main() {
	var wg sync.WaitGroup
	var m runtime.MemStats
	qtdRoutines, err := strconv.Atoi("150")

	if err != nil {
		fmt.Printf("%v\n", err)
	}

	runtime.ReadMemStats(&m)
	memoriaInicio := m.TotalAlloc

	for i := 0; i < qtdRoutines; i++ {
		wg.Add(1)

		go func(number int) {
			defer wg.Done()

			fmt.Fprintf(os.Stderr, "Routine %v\n", number)
		}(i + 1)
	}

	wg.Wait()

	runtime.ReadMemStats(&m)
	memoriaFinal := m.TotalAlloc
	
	fmt.Printf("Memoria usada: %v\n", (memoriaFinal - memoriaInicio))
}
