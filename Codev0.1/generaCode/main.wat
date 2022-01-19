(module
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32ri32 (func (param i32) (result i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $main)
(func $reserveStack (param $size i32)
   (result i32)
   get_global $MP
   get_global $SP
   set_global $MP
   get_global $SP
   get_local $size
   i32.add
   set_global $SP
   get_global $SP
   get_global $NP
   i32.gt_u
   if
   i32.const 3
   call $exception
   end
)
(func $freeStack (type $_sig_void)
   get_global $MP
   i32.load
   i32.load offset=4
   set_global $SP
   get_global $MP
   i32.load
   set_global $MP 
)
(func $NombreStruct_lechuga (type $_sig_i32ri32)
 (param $this i32) (result i32)
    (local $temp i32)
 (local $temp2 i32)
 (local $temp3 i32)
 (local $localsStart i32)
   i32.const 8
 call $reserveStack  ;; returns old MP (dynamic link)
    set_local $temp
    get_global $MP
    get_local $temp
    i32.store
    get_global $MP
    get_global $SP
    i32.store offset=4
    get_global $MP
    i32.const 8
    i32.add
    set_local $localsStart

i32.const 4
get_local $this 
i32.add
i32.const 16
i32.store     ;;Asignacion
i32.const 1
call $print     ;;print
i32.const 1
return  ;;return

 i32.const -1 
 call $freeStack)
(func $ClaseRara_lechuga (type $_sig_i32ri32)
 (param $this i32) (result i32)
    (local $temp i32)
 (local $temp2 i32)
 (local $temp3 i32)
 (local $localsStart i32)
   i32.const 8
 call $reserveStack  ;; returns old MP (dynamic link)
    set_local $temp
    get_global $MP
    get_local $temp
    i32.store
    get_global $MP
    get_global $SP
    i32.store offset=4
    get_global $MP
    i32.const 8
    i32.add
    set_local $localsStart

i32.const 0
get_local $this 
i32.add
i32.const 99
i32.store     ;;Asignacion
i32.const 9
call $print     ;;print
i32.const 9
return  ;;return

 i32.const -1 
 call $freeStack)
(func $ClaseRara_tomate (type $_sig_i32ri32)
 (param $this i32) (result i32)
    (local $temp i32)
 (local $temp2 i32)
 (local $temp3 i32)
 (local $localsStart i32)
   i32.const 28
 call $reserveStack  ;; returns old MP (dynamic link)
    set_local $temp
    get_global $MP
    get_local $temp
    i32.store
    get_global $MP
    get_global $SP
    i32.store offset=4
    get_global $MP
    i32.const 8
    i32.add
    set_local $localsStart

i32.const 0
get_local $localsStart 
i32.add
i32.const 16
 i32.add
i32.const 1
i32.store     ;;Asignacion
i32.const 16
get_local $this 
i32.add
i32.const 66
i32.store     ;;Asignacion

 i32.const -1 
 call $freeStack)
(func $lechuga (type $_sig_ri32) (result i32)
    (local $temp i32)
 (local $temp2 i32)
 (local $temp3 i32)
  (local $localsStart i32)
   i32.const 8
 call $reserveStack  ;; returns old MP (dynamic link)
    set_local $temp
    get_global $MP
    get_local $temp
    i32.store
    get_global $MP
    get_global $SP
    i32.store offset=4
    get_global $MP
    i32.const 8
    i32.add
    set_local $localsStart

i32.const 0
call $print     ;;print
i32.const 0
return  ;;return

 i32.const -1 
 call $freeStack)
(func $funcion (type $_sig_ri32) (result i32)
    (local $temp i32)
 (local $temp2 i32)
 (local $temp3 i32)
  (local $localsStart i32)
   i32.const 64
 call $reserveStack  ;; returns old MP (dynamic link)
    set_local $temp
    get_global $MP
    get_local $temp
    i32.store
    get_global $MP
    get_global $SP
    i32.store offset=4
    get_global $MP
    i32.const 8
    i32.add
    set_local $localsStart

i32.const 44
get_local $localsStart 
i32.add
i32.const 0
i32.store     ;;Declaracion
block $fin
loop  $init ;;For
i32.const 44
get_local $localsStart 
i32.add
i32.load 
i32.const 2
i32.lt_s 
i32.eqz
br_if 1
i32.const 0
get_local $localsStart 
i32.add
i32.const 4
i32.const 44
get_local $localsStart 
i32.add
i32.load 
i32.mul
 i32.add
i32.load 
call $print     ;;print
i32.const 44
get_local $localsStart 
i32.add
i32.const 44
get_local $localsStart 
i32.add
i32.load 
i32.const 1
i32.add 
i32.store     ;;Asignacion
i32.const 44
get_local $localsStart 
i32.add
i32.load 
set_local $temp3
br 0
end ;;finFor
end
i32.const 44
get_local $localsStart 
i32.add
i32.const 0
i32.store     ;;Declaracion
block $fin
loop  $init ;;For
i32.const 44
get_local $localsStart 
i32.add
i32.load 
i32.const 2
i32.lt_s 
i32.eqz
br_if 1
i32.const 48
get_local $localsStart 
i32.add
i32.const 0
i32.store     ;;Declaracion
block $fin
loop  $init ;;For
i32.const 48
get_local $localsStart 
i32.add
i32.load 
i32.const 2
i32.lt_s 
i32.eqz
br_if 1
i32.const 8
get_local $localsStart 
i32.add
i32.const 8
i32.const 44
get_local $localsStart 
i32.add
i32.load 
i32.mul
 i32.add
i32.const 4
i32.const 48
get_local $localsStart 
i32.add
i32.load 
i32.mul
 i32.add
i32.load 
call $print     ;;print
i32.const 48
get_local $localsStart 
i32.add
i32.const 48
get_local $localsStart 
i32.add
i32.load 
i32.const 1
i32.add 
i32.store     ;;Asignacion
i32.const 48
get_local $localsStart 
i32.add
i32.load 
set_local $temp3
br 0
end ;;finFor
end
i32.const 44
get_local $localsStart 
i32.add
i32.const 44
get_local $localsStart 
i32.add
i32.load 
i32.const 1
i32.add 
i32.store     ;;Asignacion
i32.const 44
get_local $localsStart 
i32.add
i32.load 
set_local $temp3
br 0
end ;;finFor
end
i32.const 24
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 4
 i32.add
i32.load 
call $print     ;;print
i32.const 24
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 0
 i32.add
i32.load 
call $print     ;;print
i32.const 24
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 8
 i32.add
i32.load 
call $print     ;;print
i32.const 24
get_local $localsStart 
i32.add
i32.const 0
 i32.add
i32.load 
call $print     ;;print
i32.const 0
return  ;;return

 i32.const -1 
 call $freeStack)
(func $main (type $_sig_void)
    (local $temp i32)
 (local $temp2 i32)
 (local $temp3 i32)
  (local $localsStart i32)
   i32.const 88
 call $reserveStack  ;; returns old MP (dynamic link)
    set_local $temp
    get_global $MP
    get_local $temp
    i32.store
    get_global $MP
    get_global $SP
    i32.store offset=4
    get_global $MP
    i32.const 8
    i32.add
    set_local $localsStart

    ;;Declaracion sin lado derecho
i32.const 20
get_local $localsStart 
i32.add
i32.const 0
i32.store     ;;Declaracion
block $fin
loop  $init ;;For
i32.const 20
get_local $localsStart 
i32.add
i32.load 
i32.const 2
i32.lt_s 
i32.eqz
br_if 1
i32.const 24
get_local $localsStart 
i32.add
i32.const 0
i32.store     ;;Declaracion
block $fin
loop  $init ;;For
i32.const 24
get_local $localsStart 
i32.add
i32.load 
i32.const 2
i32.lt_s 
i32.eqz
br_if 1
i32.const 4
get_local $localsStart 
i32.add
i32.const 8
i32.const 20
get_local $localsStart 
i32.add
i32.load 
i32.mul
 i32.add
i32.const 4
i32.const 24
get_local $localsStart 
i32.add
i32.load 
i32.mul
 i32.add
i32.const 2
i32.const 20
get_local $localsStart 
i32.add
i32.load 
i32.mul 
i32.const 24
get_local $localsStart 
i32.add
i32.load 
i32.add 
i32.const 10
i32.add 
i32.store     ;;Asignacion
i32.const 24
get_local $localsStart 
i32.add
i32.const 24
get_local $localsStart 
i32.add
i32.load 
i32.const 1
i32.add 
i32.store     ;;Asignacion
i32.const 24
get_local $localsStart 
i32.add
i32.load 
set_local $temp3
br 0
end ;;finFor
end
i32.const 20
get_local $localsStart 
i32.add
i32.const 20
get_local $localsStart 
i32.add
i32.load 
i32.const 1
i32.add 
i32.store     ;;Asignacion
i32.const 20
get_local $localsStart 
i32.add
i32.load 
set_local $temp3
br 0
end ;;finFor
end
    ;;Declaracion sin lado derecho
i32.const 20
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 4444
i32.store     ;;Asignacion
i32.const 20
get_local $localsStart 
i32.add
i32.const 0
 i32.add
i32.const 6666
i32.store     ;;Asignacion
i32.const 20
get_local $localsStart 
i32.add
i32.const 8
 i32.add
i32.const 2000
i32.store     ;;Asignacion
    ;;Declaracion sin lado derecho
    ;;Declaracion sin lado derecho
i32.const 32
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 4
 i32.add
i32.const 4545
i32.store     ;;Asignacion
i32.const 32
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 0
 i32.add
i32.const 3434
i32.store     ;;Asignacion
i32.const 32
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 8
 i32.add
i32.const 1212
i32.store     ;;Asignacion
i32.const 32
get_local $localsStart 
i32.add
i32.const 0
 i32.add
i32.const 454545
i32.store     ;;Asignacion
i32.const 52
get_local $localsStart 
i32.add
i32.const 0
 i32.add
i32.const 0
i32.store     ;;Asignacion
i32.const 52
get_local $localsStart 
i32.add
i32.const 16
 i32.add
i32.const 0
i32.store     ;;Asignacion
i32.const 52
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 4
 i32.add
i32.const 0
i32.store     ;;Asignacion
get_global $SP
 i32.const 8
 i32.add
 set_local $temp
i32.const 4
get_local $localsStart 
i32.add
i32.const 8
i32.const 0
i32.mul
 i32.add

 get_local $temp 
i32.const 2
 call $copyn
get_local $temp
 i32.const 8
 i32.add
 set_local $temp
i32.const 4
get_local $localsStart 
i32.add

 get_local $temp 
i32.const 4
 call $copyn
get_local $temp
 i32.const 16
 i32.add
 set_local $temp
i32.const 32
get_local $localsStart 
i32.add

 get_local $temp 
i32.const 5
 call $copyn
get_local $temp
 i32.const 20
 i32.add
 set_local $temp
call $funcion;; Llamada a funcion 
 set_local $temp3
i32.const 1111111111
call $print     ;;print
get_global $SP
 i32.const 8
 i32.add
 set_local $temp
call $lechuga;; Llamada a funcion 
 call $print     ;;print
get_global $SP
 i32.const 8
 i32.add
 set_local $temp
i32.const 32
get_local $localsStart 
i32.add
i32.const 0
 i32.add
call $ClaseRara_lechuga;; Llamada a funcion de objeto 
 call $print     ;;print
get_global $SP
 i32.const 8
 i32.add
 set_local $temp
i32.const 32
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 0
 i32.add
call $NombreStruct_lechuga;; Llamada a funcion de objeto 
 call $print     ;;print
i32.const 111111
call $print     ;;print
i32.const 32
get_local $localsStart 
i32.add
i32.const 0
 i32.add
i32.load 
call $print     ;;print
i32.const 32
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 4
 i32.add
i32.load 
call $print     ;;print
i32.const 52
get_local $localsStart 
i32.add
i32.const 0
 i32.add
i32.load 
call $print     ;;print
i32.const 52
get_local $localsStart 
i32.add
i32.const 4
 i32.add
i32.const 4
 i32.add
i32.load 
call $print     ;;print
get_global $SP
 i32.const 8
 i32.add
 set_local $temp
i32.const 52
get_local $localsStart 
i32.add

 get_local $temp 
i32.const 5
 call $copyn
get_local $temp
 i32.const 20
 i32.add
 set_local $temp
i32.const 32
get_local $localsStart 
i32.add
i32.const 0
 i32.add
call $ClaseRara_tomate;; Llamada a funcion de objeto 
 set_local $temp3
i32.const 32
get_local $localsStart 
i32.add
i32.const 16
 i32.add
i32.load 
call $print     ;;print
i32.const 52
get_local $localsStart 
i32.add
i32.const 16
 i32.add
i32.load 
call $print     ;;print

 call $freeStack)
(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
   (param $src i32)
   (param $dest i32)
   (param $n i32)
   block
     loop
       get_local $n
       i32.eqz
       br_if 1
       get_local $n
       i32.const 1
       i32.sub
       set_local $n
       get_local $dest
       get_local $src
       i32.load
       i32.store
       get_local $dest
       i32.const 4
       i32.add
       set_local $dest
       get_local $src
       i32.const 4
       i32.add
       set_local $src
       br 0
     end
   end
))