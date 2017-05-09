/**
  * Created by ahan on 09/05/2017.



package bobsrocckets{
  package navigation{
    private[bobsrockets] class Navigator{
      protected[navigation] def useStarChart(){}
      class LegOfJourney{
        private[Navigator] val distance = 100
      }
      private[this] var speed = 200
    }
  }
  package launch{
    import navigation._
    object Vehicle{
      private[launch] val guide = new Navigator
    }
  }
}
  */