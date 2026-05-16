package com.paritosh.lru;

import com.paritosh.lru.service.CacheService;
import com.paritosh.lru.service.LRUCacheServiceImpl;

import java.util.Scanner;

public class LRUCacheApplication {
    static void main() {

        CacheService cacheService = new LRUCacheServiceImpl(3);

        Scanner scanner = new Scanner(System.in);

        String put = "put";
        String get = "get";
        String exit = "exit";

        while (true) {
            String userCommand = scanner.nextLine();
            if (userCommand == null || "".equalsIgnoreCase(userCommand)) {
                System.out.println("please enter a valid command");
                continue;
            }
            String[] commands = userCommand.split(" ");
            if (put.equalsIgnoreCase(commands[0])) {
                String key = commands[1];
                String value = commands[2];
                System.out.println(cacheService.put(key, value));
            } else if (get.equalsIgnoreCase(commands[0])) {
                String key = commands[1];
                System.out.println(cacheService.get(key));
            } else if (exit.equalsIgnoreCase(commands[0])) {
                System.out.println("closing the application");
                break;
            } else {
                System.out.println("please enter a valid command");
            }
        }

    }
}


/*
capacity = 3

Cache : [  {4, 8}  {8, 16}  {10, 20}  ]

Removed Keys in Order: [  {5, 10}  {6, 12}  ]

1. put 6 12
2. get 6 (12)
3. put 5 10
4. put 8 16
5. get 6 (12)
6. put 4 8
7. get 5 (-1)
8. get 8 (16)
9. put 10 20
10. get 6 (-1)


output in order:
2. 12
5. 12
5. -1
8. 16
10. -1



*/