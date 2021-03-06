package com.kabasoft.iws.dao

import java.util.{Calendar, Date}
import scalaz.{Store => _, Scalaz, Foldable, ~>, Id}
import Scalaz._
import  scalaz.Foldable
import Id.Id
import scalaz.effect.IO
import scalaz.stream.Process
import scalaz.concurrent.Task
import doobie.imports._
import doobie.syntax.string._
import doobie.util.transactor.DriverManagerTransactor
import doobie.hi.ConnectionIO
import com.kabasoft.iws.shared._
import com.kabasoft.iws.dao._
import com.kabasoft.iws.shared.DAO
import com.kabasoft.iws.shared.Model._
import com.kabasoft.iws.dao.Queries._


object DAOObjects  {

  val xa= DriverManagerTransactor[Task]("org.postgresql.Driver","jdbc:postgresql:world","postgres","")

  implicit def accountDAO = new DAO[Account]
  {
     def insert(model: List[Account]):Int = Update[Account] (Queries.accountInsertSQL).updateMany(model).transact(xa).run
     def create = Queries.createAccount.run.transact(xa).run
     def update(model:Account) :Int = Queries.accountUpdateName(model).run.transact(xa).run
     def delete(id:String):Int = Queries.accountDelete(id).run.transact(xa).run
     def all :List[Account]= Queries.accountSelect.process.list.transact(xa).run
     def find(id:String) : List[Account] = Queries.accountIdSelect(id).process.list.transact(xa).run
     def findSome(id:String) = Queries.accountSelectSome(id).process.list.transact(xa).run
     def findSome1(id:Long) = Queries.accountSelectSome(id+"").process.list.transact(xa).run
  }

  implicit def articleDAO = new DAO[Article]
  {
     def insert( model: List[Article]) :Int = Update[Article] (Queries.articleInsertSQL).updateMany(model).transact(xa).run
     def create = Queries.createArticle.run.transact(xa).run
     def update(model:Article):Int = Queries.articleUpdateName(model).run.transact(xa).run
     def delete(id:String):Int = Queries.articleDelete(id).run.transact(xa).run
     def all = Queries.articleSelect.process.list.transact(xa).run
     def find(id:String) : List[Article] = Queries.articleIdSelect(id).process.list.transact(xa).run
     def findSome(id:String) = articleWithQtyUnit(id).process.list.transact(xa).run
     def findSome1(id:Long) = Queries.articleWithQtyUnit(id+"").process.list.transact(xa).run
  }

  implicit def categoryDAO = new DAO[ArticleGroup]
  {
    def insert( model: List[ArticleGroup]) :Int = Update[ArticleGroup] (Queries.categoryInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createCategory.run.transact(xa).run
    def update(model:ArticleGroup):Int = Queries.categoryUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.categoryDelete(id).run.transact(xa).run
    def all = Queries.categorySelect.process.list.transact(xa).run
    def find(id:String) : List[ArticleGroup] = Queries.categoryIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.categorySelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.categorySelectSome(id+"").process.list.transact(xa).run
  }
  implicit def customerDAO = new DAO[Customer]
  {
    def insert(model: List[Customer]) :Int =  Update[Customer](Queries.customerInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createCustomer.run.transact(xa).run
    def update(model:Customer) = Queries.customerUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.customerDelete(id).run.transact(xa).run
    def all= Queries.customerSelect.process.list.transact(xa).run
    def find(id:String) : List[Customer] = Queries.customerIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.customerSelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.customerSelectSome(id+"").process.list.transact(xa).run
  }
  
  implicit def supplierDAO = new DAO[Supplier]
  {
    def insert(model: List[Supplier]) :Int =  Update[Supplier](Queries.supplierInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createSupplier.run.transact(xa).run
    def update(model:Supplier) = Queries.supplierUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.supplierDelete(id).run.transact(xa).run
    def all = Queries.supplierSelect.process.list.transact(xa).run
    def find(id:String) : List[Supplier] = Queries.supplierIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.supplierSelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.supplierSelectSome(id+"").process.list.transact(xa).run
  }
    implicit def storeDAO = new DAO[Store]
  {
    def insert(model: List[Store]) :Int =  Update[Store](Queries.storeInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createStore.run.transact(xa).run
    def update(model:Store) = Queries.storeUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.storeDelete(id).run.transact(xa).run
    def all = Queries.storeSelect.process.list.transact(xa).run
    def find(id:String) : List[Store] = Queries.storeIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.storeSelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.storeSelectSome(id+"").process.list.transact(xa).run
  }

  implicit def costCenterDAO = new DAO[CostCenter]
  {
    def insert(model: List[CostCenter]) :Int =  Update[CostCenter](Queries.costCenterInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createCostCenter.run.transact(xa).run
    def update(model:CostCenter) = Queries.costCenterUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.costCenterDelete(id).run.transact(xa).run
    def all = Queries.costCenterSelect.process.list.transact(xa).run
    def find(id:String)  : List[CostCenter] = Queries.costCenterIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.costCenterSelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.costCenterSelectSome(id+"").process.list.transact(xa).run
  }
  implicit def quantityUnitDAO = new DAO[QuantityUnit]
  {
    def insert(model: List[QuantityUnit]) :Int =  Update[QuantityUnit](Queries.quantityUnitInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createQuantityUnit.run.transact(xa).run
    def update(model:QuantityUnit) = Queries.quantityUnitUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.quantityUnitDelete(id).run.transact(xa).run
    def all = Queries.quantityUnitSelect.process.list.transact(xa).run
    def find(id:String)  : List[QuantityUnit] = Queries.quantityUnitIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.quantityUnitSelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.quantityUnitSelectSome(id+"").process.list.transact(xa).run
  }
  implicit def vatDAO = new DAO[Vat]
  {
    def insert(model: List[Vat]) :Int =  Update[Vat](Queries.vatInsertSQL).updateMany(model).transact(xa).run
    def create = Queries.createVat.run.transact(xa).run
    def update(model:Vat) = Queries.vatUpdateName(model).run.transact(xa).run
    def delete(id:String):Int = Queries.vatDelete(id).run.transact(xa).run
    def all = Queries.vatSelect.process.list.transact(xa).run
    def find(id:String)  : List[Vat] = Queries.vatIdSelect(id).process.list.transact(xa).run
    def findSome(id:String) = Queries.vatSelectSome(id).process.list.transact(xa).run
    def findSome1(id:Long) = Queries.vatSelectSome(id+"").process.list.transact(xa).run
  }

    implicit def linePurchaseOrderDAO = new DAO[LinePurchaseOrder]{
    def insert(model: List[LinePurchaseOrder]) :Int = {
      println(s" inserting ${model}  items")
      val tid:Long = Queries.getSequence ("LinePurchaseOrder", "id").unique.transact(xa).run;
      println(s"  getSequence ${tid} ")
      val ret= doobie.imports.Update[(Long, Long, Int,String,String,BigDecimal,BigDecimal,String,Date,String)](Queries.linePurchaseOrderInsertSQL).
        updateMany(model.filter(_.item != None).map( x=>(tid, x.transid, x.modelId,x.item.get, x.unit.get, x.price, x.quantity,x.vat.get,x.duedate.get,x.text))).transact(xa).run
      ret
    }
    def create = Queries.createLinePurchaseOrder.run.transact(xa).run
    def update(model:LinePurchaseOrder) = { println(s" updating ${model}  items"); Queries.linePurchaseOrderUpdateName(model).run.transact(xa).run}
    def delete(id:String):Int = Queries.linePurchaseOrderDelete(id.toLong).run.transact(xa).run
    def all:List[LinePurchaseOrder] =  Queries.linePurchaseOrderSelect.process.list.transact(xa).run.map(x =>
                                LinePurchaseOrder(x._1,x._2, x._3, Some(x._4),Some(x._5), x._6, x._7,  Some(x._8), Some(x._9), x._10))
    def  find(id:String):List[LinePurchaseOrder] = Queries.linePurchaseOrderIdSelect(id.toLong).process.list.transact(xa).run.map(x =>
                            LinePurchaseOrder(x._1,x._2, x._3, Some(x._4),Some(x._5), x._6, x._7,  Some(x._8), Some(x._9), x._10))
    def findSome(id:String) = Queries.linePurchaseOrderSelectSome(id).process.list.transact(xa).run.map(x =>
                          LinePurchaseOrder(x._1,x._2, x._3, Some(x._4),Some(x._5), x._6, x._7,  Some(x._8), Some(x._9), x._10))
    def findSome1(id:Long) = Queries.linePurchaseOrderIdSelect(id).process.list.transact(xa).run.map(x => LinePurchaseOrder(x._1,x._2, x._3, Some(x._4),
                                                                                   Some(x._5), x._6, x._7,  Some(x._8), Some(x._9), x._10))
  }

implicit def purchaseOrderDAO = new DAO[PurchaseOrder[LinePurchaseOrder]]{ 
  def predicate(p:LinePurchaseOrder) = p.id==0
  def insert(model: List[PurchaseOrder[LinePurchaseOrder]]) :Int = {
      val tid:Long = Queries.getSequence ("purchaseorder", "id").unique.transact(xa).run;      
      val ret= doobie.imports.Update[(Long,Long, Int, String,String)](Queries.purchaseOrderInsertSQL).updateMany(model.map(
                     x=>(tid, x.oid, x.modelId, x.store.get,x.account.get))).transact(xa).run;
      model.map( x=>implicitly[DAO[LinePurchaseOrder]].insert(x.lines.getOrElse(List[LinePurchaseOrder]()).map( z => z.copy(transid=tid))))
      ret
      
     }
    def create = Queries.createPurchaseOrder1.run.transact(xa).run
    def update(model:PurchaseOrder[LinePurchaseOrder]) = {
      val ret = Queries.purchaseOrderUpdateName(model).run.transact(xa).run;
      def prdicate (pred:LinePurchaseOrder) = pred.tid == 0L
      val t:(List[LinePurchaseOrder], List[LinePurchaseOrder]) = model.getLines.partition(prdicate)
      val t1 :List[LinePurchaseOrder] = t._1
      val t2 : List[LinePurchaseOrder]= t._2

      val k1= model.getLines.partition(_.modified)._1.map (linePurchaseOrderDAO.update )
      val k2 = model.getLines.partition(_.deleted)._1.map ( e =>linePurchaseOrderDAO.delete(e.id.toString) )

      println(s" PO Line deleted K2 ${k2}   deleted");
      println(s" t1 ${t1}  items");
      println(s" t2 ${t2}  items");
      linePurchaseOrderDAO.insert (t1);
      t2.map (linePurchaseOrderDAO.update );
      ret
    }
    def delete(id:String):Int = Queries.purchaseOrderDelete(id.toLong).run.transact(xa).run
    def all:List[PurchaseOrder[LinePurchaseOrder]] = Queries.purchaseOrderSelect.process.list.transact(xa).run.map(
                       x => PurchaseOrder(x._1,x._2, x._3, Some(x._4),Some(x._5)).copy(lines = Some(f(x._1))))
    def find(id:String)  : List[PurchaseOrder[LinePurchaseOrder]] =
               Queries.purchaseOrderIdSelect(id.toLong).process.list.transact(xa).run.map(
                      x=> PurchaseOrder(x._1,x._2, x._3, Some(x._4), Some(x._5)).copy(lines = Some(f(x._1))))
    def findSome(id:String) = Queries.purchaseOrderSelectSome(id).process.list.transact(xa).run.map(
                      x=> PurchaseOrder(x._1, x._2, x._3, Some(x._4), Some(x._5)).copy(lines = Some(f(x._1))))
    def f (a:Long) = implicitly[DAO[LinePurchaseOrder]].findSome1(a)
    def findSome1(id:Long) =Queries.purchaseOrderIdSelect(id).process.list.transact(xa).run.map(
                     x=> PurchaseOrder(x._1,x._2, x._3, Some(x._4), Some(x._5)).copy(lines = Some(f(x._1))))
    def  updateLineId(id:Long,line:LinePurchaseOrder):LinePurchaseOrder = line.copy(transid=id)
  }

  def create: ConnectionIO[Int] = Queries.create.run

  def runNonParameterizedSingleObjectQuery[A](q: Query0[A]) = q.unique.transact(xa).run
  def processNonParameterizedQuery[A](q: Query0[A]) = q.process.transact(xa)

}

