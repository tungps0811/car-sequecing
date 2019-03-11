<?php
class Projet extends Controller{
  public function index() {
    $this->accueil();
  }
  
  public function ranking() {
  $team_names = $this->soccer->team_names();
  $ranking = $this->soccer->ranking();
  $title = 'Classement de la ligue de football ';
  $this->loader->load('ranking', ['title'=>$title, 'team_names'=>$team_names, 'ranking'=>$ranking]);
  }

public function algo() {
    $title ='algo' ;
    $this->loader->load('algo', ['title'=>$title]);
  }
  public function formulaire() {
    $title ='formulaire' ;
    $this->loader->load('formulaire', ['title'=>$title]);
  }
  public function questions() {
    $title ='questions' ;
    $this->loader->load('questions', ['title'=>$title]);
  }
  public function nous() {    
    $title ='nous' ;
    $this->loader->load('nous', ['title'=>$title]);
  }
  public function accueil() {
    $title ='accueil' ;
    $this->loader->load('accueil', ['title'=>$title]);
  }
} 