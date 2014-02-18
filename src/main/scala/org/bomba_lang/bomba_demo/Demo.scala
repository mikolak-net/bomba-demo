package org.bomba_lang.bomba_demo

import org.bomba_lang.proto._

object Demo {

  def main(args: Array[String]): Unit = {

    @bomba
    val prog1 = { z(1, 2) }

    println(prog1)

    @bomba
    val prog2 = {
      y v z
    }

    println(prog2.solve)

    @bomba
    val prog3 = {
      rain :- wet
      wet
    }

    println(prog3.solve)

    println("canonical program extension")
    @bomba
    val r0 = {
      rain :- wet
    }
    println(r0.solve)
    @bomba
    val r1 = {
      wet
    }
    println(r0.solve(r1))
    
    println("-------------------")
    println("canonical program extension with default negation")
    @bomba
    val rn0 = {
      rain :- wet & ~sprinkler
    }
    println(r0.solve)
    @bomba
    val rn1 = {
      wet
    }
    println(rn0.solve(rn1))
    @bomba
    val rn2 = {
      wet
      sprinkler
    }
    println(rn0.solve(rn2))
    
    println("-------------------")
    println("variables")
    @bomba
    val varProg = {
      x(1)
      y(2)
      z('X, 'Y) :- x('X) & y('Y)
    }
    println("Ungrounded: " + varProg)

    val varProgGround = new GroundProgram(varProg)
    println("Grounded: " + varProgGround)
    println("Result: " + varProgGround.solve)
    
    
    println("-------------------")
    println("Constraints")
    @bomba
    val constProg = {
      x
      Nil :- x
    }
    println(constProg)
    println(constProg.solve) //empty set (*no* answer sets)
    
    println("-------------------")
    println("\"Formal\" notation")
    @bomba
    val formalProg = {
      a ∨ b ⟵ x ∧ y ∧ z
      ⊥ ⟵ z ∧ d
    }
    println(formalProg)

  }

}