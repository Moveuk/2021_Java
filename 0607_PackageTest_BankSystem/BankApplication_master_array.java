package com.exam20;

import java.util.Scanner;

public class BankApplication {
   private static Account[] accountArray = new Account[100];
   private static Scanner scanner = new Scanner(System.in);
   
   public static void main(String[] args) {
      boolean run = true;      
      while(run) {
         System.out.println("----------------------------------------------------------");
         System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.이체 | 6.종료");
         System.out.println("----------------------------------------------------------");
         System.out.print("선택> ");
         
         int selectNo = scanner.nextInt();
         
         if(selectNo == 1) {
            createAccount();
         } else if(selectNo == 2) {
            accountList();
         } else if(selectNo == 3) {
            deposit();
         } else if(selectNo == 4) {
            withdraw();
         } else if(selectNo == 5) {
            transfer();
         }else if(selectNo == 6) {
            run = false;
         }
      }
      System.out.println("프로그램 종료");
   }
   
   //계좌생성하기
   private static void createAccount() {
      System.out.println("--------------");
      System.out.println("계좌생성");
      System.out.println("--------------");
      
      System.out.print("계좌번호: "); 
      String ano = scanner.next();
      
      System.out.print("계좌주: "); 
      String owner = scanner.next();
      
      System.out.print("초기입금액: ");
      int balance = scanner.nextInt();
      
      Account newAccount = new Account(ano, owner, balance);
      for(int i=0; i<accountArray.length; i++) {
         if(accountArray[i] == null) {
            accountArray[i] = newAccount;
            System.out.println("결과: 계좌가 생성되었습니다.");
            break;
         }
      }
   }
   
   //계좌목록보기
   private static void accountList() {
      System.out.println("--------------");
      System.out.println("계좌목록");
      System.out.println("--------------");
      for(int i=0; i<accountArray.length; i++) {
         Account account = accountArray[i];
         if(account != null) {
            System.out.print(account.getAno());
            System.out.print("     ");
            System.out.print(account.getOwner());
            System.out.print("     ");
            System.out.print(account.getBalance());
            System.out.println();
         }
      }
   }
   
   //예금하기
   private static void deposit() {
      System.out.println("--------------");
      System.out.println("예금");
      System.out.println("--------------");
      System.out.print("계좌번호: "); 
      String ano = scanner.next();
      System.out.print("예금액: ");
      int money = scanner.nextInt();
      Account[] account = findAccount(ano);
      if(account == null) {
         System.out.println("결과: 계좌가 없습니다.");
         return;
      }
      account[0].setBalance(account[0].getBalance() + money);
      System.out.println("결과: 예금이 성공되었습니다.");
   }
   
   //출금하기
   private static void withdraw() {
      System.out.println("--------------");
      System.out.println("출금");
      System.out.println("--------------");
      System.out.print("계좌번호: "); 
      String ano = scanner.next();
      System.out.print("출금액: ");
      int money = scanner.nextInt();
      Account[] account = findAccount(ano);
      if(account == null) {
         System.out.println("결과: 계좌가 없습니다.");
         return;
      }
      account[0].setBalance(account[0].getBalance() - money);
      System.out.println("결과: 출금이 성공되었습니다.");
   }
   
   //이체하기
   private static void transfer() {
      System.out.println("---------------");
      System.out.println("이체");
      System.out.println("---------------");
      
      System.out.println("보내시는 분의 계좌번호: ");
      String ano = scanner.next();
      
      System.out.println("받으실 분의 계좌번호: ");
      String ano1 = scanner.next();
      
      System.out.println("이체액: ");
      int money = scanner.nextInt();
      
      Account[] acc = findAccount(ano,ano1);
      //Account acc1 = findAccount(ano1);

      if(acc[0] == null || acc[1] == null) {
         System.out.println("계좌가 존재하지 않습니다.");
         return;
      }
      if(money <= acc[0].getBalance()) {
      acc[0].setBalance(acc[0].getBalance() - money);
      acc[1].setBalance(acc[1].getBalance() + money);
      }else {
         System.out.println("잔액이 부족합니다.");
      }
   }
      
   
   
   private static Account[] findAccount(String ... ano) {
      
      Account[] account = new Account[ano.length];
      
      for(int i=0; accountArray[i] != null; i++) {
         
         String dbAno = accountArray[i].getAno();
         
         for(int j=0; j < ano.length; j++) { 
            if(dbAno.equals(ano[j])) {
               account[j] = accountArray[i];
               break;
            }
         }
      }
      return account;
   }
}
